/**
 * Created by liurenhua on 2017/11/9.
 */
var code = commonClass.getQueryString("materialCode");

var viewMaterialAttributeVOList = [];
var viewMaterialAttribteValueList = [];
var selectItemList = [];
$(function () {
    $("#loading").modal('show');
    //获取属性信息、
    if (code != null && code.length != 0) {
        $.ajax({
            type: "GET",
            contentType: "application/json;charset=utf-8",
            url: webroot + "/material/findattributebycode",
            data: {
                "materialCode": code
            },
            success: function (response) {
                if (response.code == 200) {
                    var data = response.data;
                    if (data == null) {
                        $("#loading").modal('hide');
                        swal(ICICLELangUtil.getText(language, 1148), ICICLELangUtil.getText(language, 1557), "error");
                    }
                    viewMaterialAttributeVOList = data.viewMaterialAttributeVOList;
                    selectItemList = data.viewSelectItemList;
                    viewMaterialAttribteValueList = data.materialAttributeVOList;
                    $("#materialCode").val(data.viewMaterialDetailVO.materialCode);
                    $("#materialName").val(data.viewMaterialDetailVO.materialName);
                    $("#materialDesc").val(data.viewMaterialDetailVO.materialDesc);
                    $("#uom").val(data.viewMaterialDetailVO.uom);
                    $("#bigClass").val(data.viewMaterialDetailVO.cateDlName);
                    $("#midClass").val(data.viewMaterialDetailVO.cateZlName);
                    $("#smallClass").val(data.viewMaterialDetailVO.cateXlName);
                    $("#vendorPrice").val(data.viewMaterialDetailVO.vendorPrice);
                    $("#bigProdectPrice").val(data.viewMaterialDetailVO.unitPrice);
                    if (data.viewMaterialDetailVO.syncStatus == 0) {
                        $("#erpStatus").val("已存在");
                    } else if (data.viewMaterialDetailVO.syncStatus == 1) {
                        $("#erpStatus").val("待更新");
                    } else {
                        $("#erpStatus").val("新增");
                    }
                    if (data.status) {
                        $("#enable").val(ICICLELangUtil.getText(language, 1092));
                    } else {
                        $("#enable").val(ICICLELangUtil.getText(language, 1091));
                    }
                    loadAttribute();
                    $("#loading").modal('hide');
                }
                else {
                    $("#loading").modal('hide');
                    swal(ICICLELangUtil.getText(language, 1148), response.message, "error");
                }
            },
            error: function () {
                $("#loading").modal('hide');
                swal(ICICLELangUtil.getText(language, 1148), ICICLELangUtil.getText(language, 1105), "error");
            }
        });
    } else {
        location.href = webroot + "/material/material";
    }

    $("#save").click(function () {
        var list = [];

        for (var i = 0; i < viewMaterialAttributeVOList.length; i++) {
            var viewMaterialAttributeVO = viewMaterialAttributeVOList[i];
            if (viewMaterialAttributeVO.isEdit == 1) {
                viewMaterialAttributeVO.atrValue = $("#" + viewMaterialAttributeVO.attrCode).val();
                var id = getAttributeId(viewMaterialAttributeVO.attrCode);
                list.push({
                    attrCode: viewMaterialAttributeVO.attrCode,
                    atrValue: $("#" + viewMaterialAttributeVO.attrCode).val(),
                    id: id
                });
            }
        }

        var data = {
            materialCode: code,
            materialAttributeList: list
        }

        $.ajax({
            type: "POST",
            contentType: "application/json;charset=utf-8",
            url: webroot + "/material/updateattribute",
            data: JSON.stringify(data),
            success: function (response) {
                if (response.code == 200) {
                    swal({
                            title: ICICLELangUtil.getText(language, 1005),
                            text: "",
                            type: "success",
                            showCancelButton: false,
                            confirmButtonClass: "btn-success",
                            confirmButtonText: ICICLELangUtil.getText(language, 1007),
                            closeOnConfirm: true
                        },
                        function () {
                            location.href = webroot + "/material/material";
                        });
                }
                else {
                    swal(ICICLELangUtil.getText(language, 1148), response.message, "error");
                }
            },
            beforeSend: function (XMLHttpRequest) {
                $("#save").attr("disabled", "disabled");
                $('#loading').modal('show');
            },
            complete: function (XMLHttpRequest, textStatus) {
                $("#save").removeAttr("disabled");
                $('#loading').modal('hide');
            },
            error: function () {
                swal(ICICLELangUtil.getText(language, 1148), ICICLELangUtil.getText(language, 1105), "error");
            }
        });
    });
});

//加载此原材料的各个属性
function loadAttribute() {
    for (var i = 0; i < viewMaterialAttributeVOList.length; i++) {
        var viewMaterialAttribute = viewMaterialAttributeVOList[i];
        var html = "<div class='form-group col-md-6'>";
        html += "<label class='control-label col-md-4'>" + viewMaterialAttribute.attrName + "</label>";
        html += "<div class='col-md-5'>"
        html += "<div class='input-icon right'>";
        html += "<i class='fa'></i>"
        if (viewMaterialAttribute.isEdit == 0) {
            html += "<input type='text' disabled   id='" + viewMaterialAttribute.attrCode + "'  class='form-control'/>";
        } else {
            if (viewMaterialAttribute.hasItem) {
                var itemList = getMySelectItem(viewMaterialAttribute.itemSource);
                html += "<select class='form-control green'  id='" + viewMaterialAttribute.attrCode + "' >"
                if (itemList == null || itemList.length == 0) {
                    html += "<option value='0'>否</option>";
                    html += "<option value='1'>是</option>";
                } else {
                    for (var j = 0; j < itemList.length; j++) {
                        var item = itemList[j];
                        html += "<option value='" + item.itemKey + "'>" + item.itemValue + "</option>"
                    }
                }
                html += "</select>"
            } else {
                html += "<div class='input-icon right'>"
                html += "<input type='text'   id='" + viewMaterialAttribute.attrCode + "'  class='form-control green' />";
            }
        }
        html += "</div></div></div>"
        $("#attributeArea").append(html);
    }

    for (var i = 0; i < viewMaterialAttribteValueList.length; i++) {
        var viewMaterialAttributeValueVO = viewMaterialAttribteValueList[i];
        if ($("#" + viewMaterialAttributeValueVO.attrCode) != undefined) {
            $("#" + viewMaterialAttributeValueVO.attrCode).val(viewMaterialAttributeValueVO.atrValue);
            console.log(viewMaterialAttributeValueVO.atrValue);
        }
    }
}
//获取某个下拉列表的所有选项值
function getMySelectItem(itemSource) {
    var result = [];
    for (var i = 0; i < selectItemList.length; i++) {
        if (selectItemList[i].tableName == itemSource) {
            result.push(selectItemList[i]);
        }
    }
    return result;
}

function getAttributeId(code) {
    for (var i = 0; i < viewMaterialAttribteValueList.length; i++) {
        var value = viewMaterialAttribteValueList[i];
        if (value.attrCode == code) {
            return value.id;
        }
    }
    return null;
}