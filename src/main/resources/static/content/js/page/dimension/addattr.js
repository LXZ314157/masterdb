/**
 * Created by liurenhua on 2017/12/18.
 */
var itemList = [];
var defList = [];
var valueList = [];
var selectItem = [];
var productCode = commonClass.getQueryString("productCode");
$(document).ready(function () {
    ajaxDefList();

    ajaxSelectItem();
//提交按钮
    $('#btnSaveAttr').click(function () {

        if ($("#productCode").val().length == 0) {
            //产品编码不能为空
            swal(ICICLELangUtil.getText("", 1444), "", "warning");
            return;
        }

        var submitList = [];
        for (var i = 0; i < defList.length; i++) {
            var attrDef = defList[i];
            var textValue = $("#" + attrDef.defCode).val()
            if (textValue == null || textValue.length == 0) {
                continue;
            }

            var attrValue = contain(attrDef.defCode);
            var va = getAttrValue(attrDef);
            if (attrValue == null) {
                submitList.push({
                    attrCode: attrDef.defCode,
                    attrValue: va,
                    productCode: $("#productCode").val()
                });
            } else {
                attrValue.attrValue = va;
                submitList.push(attrValue);
            }
        }
        if (submitList == null || submitList.length == 0) {
            swal("警告", "您没有填写任何属性无法提交", "warning");
            return;
        }


        $.ajax({
            type: "post",
            contentType: "application/json;charset=utf-8",
            data: JSON.stringify(submitList),
            url: webroot + "/dimension/insertproductattribute",
            success: function (response) {
                if (response.code == 200) {
                    //成功
                    swal(ICICLELangUtil.getText("", 1005), "", "success");
                }
                else {
                    swal(response.message, "", "error");
                }
            },
            beforeSend: function (XMLHttpRequest) {
                $('#loading').modal('show');
            },
            complete: function (XMLHttpRequest, textStatus) {
                $('#loading').modal('hide');
            }
        });
    });
    $('#return').click(function () {
        location.href = webroot + "/dimension/merchandlist";
    })
});

//加载属性定义列表，请求成功后将继续加载属性下拉列表
function ajaxDefList() {
    $.ajax({
        type: "post",
        url: webroot + "/dimension/listattributedefvo",
        success: function (response) {
            if (response.code == 200) {
                defList = response.data;
                ajaxItemList();
            }
            else {
                swal(response.message, "", "error");
            }
        },
        beforeSend: function (XMLHttpRequest) {
            $('#loading').modal('show');
        },
        complete: function (XMLHttpRequest, textStatus) {
            $('#loading').modal('hide');
        }
    });
}

function ajaxItemList() {
    $.ajax({
        type: "post",
        url: webroot + "/dimension/listattributeitem",
        success: function (response) {
            if (response.code == 200) {
                itemList = response.data;
                drawDiv();

                if (productCode != null) {
                    ajaxAttributeValue(productCode);
                }
            }
            else {
                swal(response.message, "", "error");
            }
        },
        beforeSend: function (XMLHttpRequest) {
            $('#loading').modal('show');
        },
        complete: function (XMLHttpRequest, textStatus) {
            $('#loading').modal('hide');
        }
    });
}

//绘制div
function drawDiv() {
    for (var i = 0; i < defList.length; i++) {
        var def = defList[i];
        var html = "  <div class='form-group col-md-6'>";
        html += "<label class='control-label col-md-4 text-right'>" + def.defName + "</label>"
        html += "<div class='col-md-6'>";
        html += "<div class='input-icon right'><i class='fa'></i>";

        if (def.hasItem == null) {
            continue;
        }

        if (def.hasItem == 0) {
            html += "<input type='text' name='" + def.defCode + "' id='" + def.defCode + "' class='form-control'>";
        } else if (def.hasItem == 1) {
            html += "<select class='form-control col-md-6 col-sm-12' id='" + def.defCode + "'>";
            if (def.defType == "bit") {
                html += "<option value=''>请选择</option>";
                html += "<option value='1'>是</option>";
                html += "<option value='0'>否</option>";
            } else {
                var myItemList = getAttrItemListByDefId(def.id);
                for (var j = 0; j < myItemList.length; j++) {
                    var myItem = myItemList[j];
                    html += "<option id='" + def.defCode + myItem.code + "' value='" + myItem.code + "'>" + myItem.name + "</option>";
                }
            }

            html += "</select>";
        } else if (def.selectTable != null && def.selectTable.length != 0) {
            var s = getSelectItemByCode(def.selectTable);
            for (var j = 0; j < s.length; j++) {
                var item = s[j];
                html += "<option value='" + item.itemValue + "'>" + item.itemKey + "</option>";
            }
        } else {
            html += "<select class='form-control select2-multiple col-md-6 col-sm-12' data-tags='true'";
            html += "data-placeholder='请选择' data-allow-clear='true' multiple id='" + def.defCode + "'>";
            var myItemList = getAttrItemListByDefId(def.id);
            for (var j = 0; j < myItemList.length; j++) {
                var myItem = myItemList[j];
                html += "<option id='" + def.defCode + myItem.code + "' value='" + myItem.code + "'>" + myItem.name + "</option>";
            }
            html += "</select>";
        }
        html += "</div></div></div>";
        $("#container").append(html);
    }
    $(".select2-multiple").select2();

}


//通过属性定义的id获取相应的下拉列表
function getAttrItemListByDefId(id) {
    var list = [];
    for (var i = 0; i < itemList.length; i++) {
        var item = itemList[i];
        if (item.defId == id) {
            list.push(item);
        }
    }
    return list;
}

//客户端根据属性编码获取属性定义
function getAttrDefByCode(code) {
    for (var i = 0; i < defList.length; i++) {
        if (defList[i].defCode == code) {
            return defList[i];
        }
    }
    return null;
}

//从服务器获取当前产品编号的属性值
function ajaxAttributeValue(productCode) {
    $.ajax({
        type: "get",
        url: webroot + "/dimension/getattributebycode?productCode=" + productCode,
        success: function (response) {
            if (response.code == 200) {
                valueList = response.data;
                $("#productCode").val(productCode);
                $("#productCode").attr("disabled", true);


                if (valueList != null && valueList.length != 0) {
                    loadAttributeValue();
                }
            }
            else {
                console.log(response.message);
            }
        },
        error: function () {
            //失败,加载数据失败
            swal(ICICLELangUtil.getText("", 1148), ICICLELangUtil.getText("", 1102), 'error');
        }
    });
}

//把服务器中已有的属性加载出来
function loadAttributeValue() {
    for (var i = 0; i < valueList.length; i++) {
        var value = valueList[i];
        var attrDef = getAttrDefByCode(value.attrCode);

        if (attrDef == null) {
            continue;
        } else if (attrDef.hasItem == 2) {
            var list = getAttrItemListByDefId(attrDef.id);
            var selectedList = [];
            for (var j = 0; j < list.length; j++) {
                var code = parseInt(list[j].code);
                var v = parseInt(value.attrValue);
                if ((code & v) != 0) {
                    selectedList.push(code);
                }
            }
            $("#" + value.attrCode).select2();
            if (selectedList.length != 0) {
                $("#" + value.attrCode).val(selectedList).select2();
            }
        }
        else {
            $("#" + value.attrCode).val(value.attrValue);
        }
    }
}

//检查这个属性在服务器中发过来的属性中是否存在
function contain(code) {
    if (valueList == null || valueList.length == 0) {
        return null;
    }

    for (var i = 0; i < valueList.length; i++) {
        var value = valueList[i];

        if (value.attrCode == code) {
            return value;
        }
    }
    return null;
}

//获取属性值，判断attrCode是否有选项，如果有选项，则执行或操作，否则直接获得文本框的值
function getAttrValue(attrDef) {
    if (attrDef.hasItem == 2) {
        var s = $("#" + attrDef.defCode).val();
        if (s == null) {
            return null;
        }
        var result = 0;
        for (var j = 0; j < s.length; j++) {
            result = parseInt(s[j]) | result;
        }
        return result;
    } else {
        return commonClass.replaceAllQuotationMarks($("#" + attrDef.defCode).val());
    }
}

function ajaxSelectItem() {
    $.ajax({
        type: "get",
        url: webroot + "/product/dropdown",
        success: function (response) {
            if (response.code == 200) {
                selectItem = response.data;
            }
            else {
                console.log(response.message);
            }
        },
        error: function () {
            //失败,加载数据失败
            swal(ICICLELangUtil.getText("", 1148), ICICLELangUtil.getText("", 1102), 'error');
        }
    });
}

function getSelectItemByCode(code) {
    var list = [];
    for (var i = 0; i < selectItem.length; i++) {
        var item = selectItem[i];
        if (code == item.itemKey) {
            list.push(item);
        }
    }
    return list;
}