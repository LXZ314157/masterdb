/**
 * Created by liurenhua on 2017/12/4.
 */
var dimensionList = [];
var attributeDefinedList = [];
var currentDimensionId = null;
var currentTr = null;
var itemList = [];
var dimensionAttriList = [];
var tableNameList = [];
var defIdList = [];
$(function () {
    init();
    $("#savaDeminsion").click(function () {
        var deminsionCode = commonClass.replaceAllQuotationMarks($("#dimensionCode").val());
        var deminsionName = commonClass.replaceAllQuotationMarks($("#dimensionName").val());
        var deminsionDesc = commonClass.replaceAllQuotationMarks($("#dimensionDesc").val());

        var status = 1;
        // var id = $("#dimensionId").val();
        status = $('#dimensionStatus .active input').val();
        if (deminsionCode.length == 0 || deminsionName.length == 0) {
            swal(ICICLELangUtil.getText("", 1164), "", "warning");
            return;
        }
        if (!commonClass.checkCode(deminsionCode)) {
            swal("编码只能为字母和下划线组合", "", "warning");
            return;
        }
        var dimension;
        if (currentDimensionId == null) {
            dimension = {
                classDimensionCode: deminsionCode,
                classDimensionName: deminsionName,
                classDimensionDesc: deminsionDesc,
                status: status
            }
        } else {
            dimension = {
                id: currentDimensionId,
                classDimensionCode: deminsionCode,
                classDimensionName: deminsionName,
                classDimensionDesc: deminsionDesc,
                status: status
            }
        }

        addOrUpdateDimension(dimension);
    });
    //新增属性定义
    $("#addAttribute").click(function () {
        $("#itemStatus").hide();
        $("#attribute_state").hide();
        //新增
        $(".modal-title").html("新增维度属性");
        $("#acode").removeAttr("disabled");
        $("#cname").removeAttr("disabled");
        $("#itemType").removeAttr("disabled");
        $("#itemLength").removeAttr("disabled");
        $("#saveDimensionAttrib").val("0");
        $("#attributeDefinedArea").modal('show');
        $("#acode").val("");
        $("#cname").val("");
        $("#itemLength").val("");
        $("#itemType").val("int");
        $('#itemcontent').hide();
        $("#edititem").html("");
        $("#hastate").addClass("active");
        $("#noSelect input").addClass("active");
        showActive("noSelect", "haveSelect", "haveMoreSelect");
        $("#item_select").show();
        $("#itemName").val("");
        $("#defdesc").val("");
    });
    //新增保存属性
    //保存维度属性
    $("#saveDimensionAttrib").click(function () {
        var hasItem = $('#stateItem .active input').val();
        var checkValue = $('#itemType  option:selected').val();
        if ($("#acode").val().length == 0 || $("#cname").val().trim().length == 0 || $("#itemType").val().length == 0) {
            //信息没有填写完全，无法提交
            swal(ICICLELangUtil.getText("", 1164), "", "warning");
            return;
        }

        if (!commonClass.checkCode($("#acode").val())) {
            swal("编码只能为字母和下划线组合", "", "warning");
            return;
        }
        if (checkValue == "bit") {
            if (hasItem != 1) {
                swal("bit类型需选择单选", "", "warning");
                return;
            }

        }

        if (hasItem == 2 && (itemList == null || itemList.length == 0)) {
            //有选项须填写选项名称
            swal(ICICLELangUtil.getText("", 1106), "", "warning");
            return;
        }

        if (hasItem == 1 && (itemList == null || itemList.length == 0) && checkValue == "int") {
            //有选项须填写选项名称
            swal(ICICLELangUtil.getText("", 1106), "", "warning");
            return;
        }
        if (hasItem != 0 && (itemList.length == 0) && $("#itemTable").val() == 0 && checkValue != 'bit') {
            swal("单选或多选时，可选表和选项添加至少填写一项", "", "warning");
            return;
        }

        if ($("#itemLength").val().length == 0) {
            $("#itemLength").val(0);
        }
        if (!commonClass.checkInt($("#itemLength").val())) {
            swal("值长度必须为整数", "", "warning");
            return;
        }
        if ($(this).val() == 0) {
            addAttributeDef(hasItem);
        } else {
            updateAttributeDef(hasItem);
        }

    });
    //添加选项
    $("#btnAddAttribute").click(function () {
        if ($("#itemName").val() == null) {
            //选项名称未填写，请输入
            swal(ICICLELangUtil.getText("", 1103), "", "warning");
            return;
        }
        //修改
        if ($(this).html() == ICICLELangUtil.getText("", 1252)) {
            $("#edititem").html("");
            for (var i = 0; i < itemList.length; i++) {
                if (itemList[i].id == $("#itemId").val()) {
                    itemList[i].name = $("#itemName").val();
                    if ($("#itemStatus .active input").val() == 0) {
                        itemList[i].status = false;
                    }
                    else {
                        itemList[i].status = true;
                    }
                }
            }
            drawItem();
            $("#itemName").val("");
            $(this).html("添加");
            $("#itemStatus").hide();
            $("#haveSelect").addClass("active");
            $("#itemName").removeAttr("disabled")
        } else {
            for (var i = 0; i < itemList.length; i++) {
                if (itemList[i].name == $("#itemName").val()) {
                    //当前选项已存在，请换个选项名
                    swal(ICICLELangUtil.getText("", 1104), "", "warning");
                    return;
                }
            }
            itemList.push({
                status: $("#itemStatus .active input").val(),
                id: $("#itemId").val(),
                name: $("#itemName").val(),
            });
            addItem($("#itemName").val());
            $("#itemName").val("");
            $("#itemDesc").val("");
        }
        $("#itemId").val(0);
    });
    //取消添加
    $("#addCancel").click(function () {
        $("#itemName").val("");
        $("#itemName").removeAttr("disabled");
        $("#itemStatus").hide();
        $("#btnAddAttribute").html("添加");
    });
    //清空itemlist
    $("#attributeCancel").click(function () {
        itemList = [];
        $("#itemTable").val(0);
        $("#itemName").val("");
        $("#btnAddAttribute").html("添加")
    })
    $(".close").click(function () {
        $("#itemName").val("");
        $("#btnAddAttribute").html("添加");
        $("#itemTable").val(0);
        itemList = [];
    });
    $("#haveSelect").click(function () {
        var vs = $('#itemType  option:selected').val();
        if (vs == "bit") {
            showOrHideItem(false);
        }
        else {
            showOrHideItem(true);
        }
    })
});
init = function () {
    $.ajax({
        type: "post",
        url: webroot + "/dimension/listattributeitems",
        success: function (response) {
            if (response.code == 200) {
                dimensionList = response.data;
                for (var i = 0; i < dimensionList.length; i++) {
                    var dimension = dimensionList[i];
                    addDimension(dimension);
                }

            }
            else {
                //失败
                swal(ICICLELangUtil.getText("", 1148), response.message, "error");
            }
        },
        error: function () {
            //您的网络似乎出了一些问题
            swal(ICICLELangUtil.getText("", 1105), "", "error");
        }
    });
    $.ajax({
        type: "post",
        url: webroot + "/dimension/listattributedefined",
        success: function (response) {
            if (response.code == 200) {
                attributeDefinedList = response.data;
                checkAndDrawConnectDef(attributeDefinedList);
                listDimensionAttri();
            }
            else {
//失败
                swal(ICICLELangUtil.getText("", 1148), response.message, "error");
            }
        },
        error: function () {
        }
    });
    $.ajax({
        type: "GET",
        url: webroot + "/dimension/loadtablename",
        success: function (response) {
            if (response.code == 200) {
                tableNameList = response.data;
                loadTableName();
            }
            else {
                //失败
                swal(ICICLELangUtil.getText("", 1148), response.message, "error");
            }
        },
        error: function () {
        }
    });
}


//验证和绘制属性定义表
function checkAndDrawConnectDef(attributeDefinedList) {
    $.ajax({
        type: "post",
        url: webroot + "/dimension/connectdefid",
        success: function (msg) {
            if (msg.code == 200) {
                defIdList = msg.data;
                drawDefAttribute(attributeDefinedList);
            }
            else {
                //失败
                swal(ICICLELangUtil.getText("", 1148), response.message, "error");
            }
        },
        error: function () {
        }
    });
}


//加载属性定义表
function drawDefAttribute(attributeDefinedList) {
    if (attributeDefinedList != null) {
        for (var i = 0; i < attributeDefinedList.length; i++) {
            var attributeDefined = attributeDefinedList[i];
            if (checkConnect(attributeDefined.id, defIdList) > 0) {
                //已经使用
                addAttrDefined(attributeDefined, true);
            }
            else {
                addAttrDefined(attributeDefined, false);
            }
        }
    }
}

//新增维度属性
function addAttributeDef(hasItem) {
    var status = 1;
    status = $('#status .active input').val();
    var requestData = {
        "defCode": commonClass.replaceAllQuotationMarks($("#acode").val()),
        "defName": commonClass.replaceAllQuotationMarks($("#cname").val()),
        "defDesc": commonClass.replaceAllQuotationMarks($("#defdesc").val()),
        "defType": commonClass.replaceAllQuotationMarks($("#itemType").val()),
        "def_group": commonClass.replaceAllQuotationMarks($("#defGroup").val()),
        "defLength": commonClass.replaceAllQuotationMarks($("#itemLength").val()),
        "hasItem": hasItem,
        "status": status,
        "itemList": itemList
    };
    if ($("#itemTable").val() != 0) {
        requestData["selectTable"] = $("#itemTable").val();
    }
    $.ajax({
        type: "POST",
        url: webroot + "/dimension/addattributedefine",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(requestData),
        success: function (data) {
            if (data.code == 200) {
                swal({
                        //成功
                        //添加成功，确定
                        title: ICICLELangUtil.getText("", 1005),
                        text: ICICLELangUtil.getText("", 1055),
                        type: "success",
                        showCancelButton: false,
                        confirmButtonClass: "btn-success",
                        confirmButtonText: ICICLELangUtil.getText("", 1007),
                        closeOnConfirm: true
                    },
                    function () {
                        location.reload();
                    });
            } else {
                //失败
                swal(ICICLELangUtil.getText("", 1148), data.message, "error");
            }
        },
        error: function () {
            //发送失败
            //网络中断
            swal(ICICLELangUtil.getText("", 1163), ICICLELangUtil.getText("", 1064), "error");
        },
        beforeSend: function (XMLHttpRequest) {
            $("#saveDimensionAttrib").attr("disabled", "disabled");
            $('#loading').modal('show');
        },
        complete: function (XMLHttpRequest, textStatus) {
            $("#saveDimensionAttrib").removeAttr("disabled");
            $('#loading').modal('hide');
        }
    });
}

//更新维度属性
function updateAttributeDef(hasItem) {
    var status = 1;
    status = $('#status .active input').val();
    var requestData = {
        "id": commonClass.replaceAllQuotationMarks($("#id").val()),
        "defCode": commonClass.replaceAllQuotationMarks($("#acode").val()),
        "defName": commonClass.replaceAllQuotationMarks($("#cname").val()),
        "defDesc": commonClass.replaceAllQuotationMarks($("#defdesc").val()),
        "defType": commonClass.replaceAllQuotationMarks($("#itemType").val()),
        "defLength": commonClass.replaceAllQuotationMarks($("#itemLength").val()),
        "hasItem": hasItem,
        "status": status,
        "itemList": itemList
    };
    if ($("#itemTable").val() != 0) {
        requestData["selectTable"] = $("#itemTable").val();
    }
    $.ajax({
        type: "POST",
        contentType: "application/json;charset=utf-8",
        url: webroot + "/dimension/updateattributedefine",
        data: JSON.stringify(requestData),
        success: function (response) {
            if (response.code == 200) {
                swal({
                        //成功
                        //更新成功，确定
                        title: ICICLELangUtil.getText("", 1005),
                        text: ICICLELangUtil.getText("", 1167),
                        type: "success",
                        showCancelButton: false,
                        confirmButtonClass: "btn-success",
                        confirmButtonText: ICICLELangUtil.getText("", 1007),
                        closeOnConfirm: true
                    },
                    function () {
                        location.reload();
                    });
            } else {
                //失败
                swal(ICICLELangUtil.getText("", 1148), response.message, "error");
            }
        },
        error: function () {
            //发送失败
            //网络中断
            swal(ICICLELangUtil.getText("", 1163), ICICLELangUtil.getText("", 1064), "error");
        },
        beforeSend: function (XMLHttpRequest) {
            $("#saveDimensionAttrib").attr("disabled", "disabled");
            $('#loading').modal('show');
        },
        complete: function (XMLHttpRequest, textStatus) {
            $("#saveDimensionAttrib").removeAttr("disabled");
            $('#loading').modal('hide');
        }
    });
}


function addDimension(dimension) {
    if (dimension.status == 1) {
        var html = "<tr><td class='detail' width='5%'>" + dimension.id + "</td>";
        html += "<td class='detail'>" + dimension.classDimensionCode + "</td>";
        html += "<td class='detail'>" + dimension.classDimensionName + "</td>"
        html += "<td class='detail' >" + dimension.classDimensionDesc + "</td>"
        html += "<td><a href='javascript:;' data-dimension='" + JSON.stringify(dimension) + "' class='btn btn-xs green btn-outline' " +
            "rel='tooltip' title='编辑' onclick='showDimension(this)'><i class='fa fa-pencil'></i></a>"
        html += "<a href='javascript:;' data-dimension='" + JSON.stringify(dimension) + "' class='btn btn-xs blue btn-outline' " +
            "rel='tooltip' title='查看关联属性' onclick='showAttribue(this)'><i class='fa fa-balance-scale'></i></a> "
        html += "<a href='javascript:;' data-dimension='" + JSON.stringify(dimension) + "' class='btn btn-xs red btn-outline' " +
            "rel='tooltip' title='关联新属性' onclick='connectAttribute(this)'><i class='fa fa-adjust'></i></a> "
        html += "<a href='javascript:;' data-dimension='" + dimension.id + "' class='btn btn-xs blue btn-outline'" +
            "rel='tooltip' title='查看列表' onclick='viewList(this)'><i class='fa fa-columns'></i></a></td> "
        $("#dimension").append(html);
    }

}

function showAttribue(cur) {
    listDimensionAttri();
    var dimensionDetail = $(cur).data('dimension');
    $("#classDimensionName").val(dimensionDetail.classDimensionName);
    $("#AttributeDimensionId").val(dimensionDetail.id);
    $("#AttributeDimensionCode").val(dimensionDetail.classDimensionCode);
    $("#checklist").html(" ");
    var flag = false;
    for (var i = 0; i < attributeDefinedList.length; i++) {
        var html = "";
        var attributeDefined = attributeDefinedList[i];
        if (attributeDefined.status == 0)
            continue;
        if (contain(dimensionDetail.id, attributeDefined.id)) {
            flag = true;
            var html = "<label class=\"mt-checkbox col-md-6\">" + attributeDefined.defName;
            html += "<input name='checkbox' type=\"checkbox\"  checked value='" + attributeDefined.id + "' onclick=\"return false;\"/>";
            html += "<span></span></label>";
        }
        $("#checklist").append(html);
    }
    if (!flag) {
        //您没有关联任何属性
        swal(ICICLELangUtil.getText("", 1076), "", "warning");
        $("#connectAttribute").modal('hide');
        return;
    }
    else {
        $("#connectAttribute").modal('show');
    }
    $("#btnConnect").hide();

}

function addAttrDefined(attrDefined, flag) {
    // if (attrDefined.status == 1) {
        var html = "<tr><td class='detail'>" + attrDefined.defCode + "</td>"
        html += "<td class='detail'>" + attrDefined.defName + "</td>"
        html += "<td class='detail'>" + attrDefined.defType + "</td>"
        html += "<td class='detail'>" + attrDefined.defLength + "</td>"
        if (attrDefined.hasItem == 1) {
            html += "<td class='detail'>有单选项</td>"
        }
        else if (attrDefined.hasItem == 2) {
            html += "<td class='detail'>有多选项</td>"
        }
        else {
            html += "<td class='detail'>无选项</td>"
        }
        if(attrDefined.status == 1){
            html += "<td class='detail'>有效</td>"
        }else{
            html += "<td class='detail'>无效</td>"
        }

        html += "<td>"
        if (!flag) {
            //可以编辑 false
            html += "<a href='javascript:;'  onclick='editDimensionAttribute(this,false)' class='btn btn-xs green btn-outline'" +
                " rel='tooltip' title='编辑' data-entiy='" + JSON.stringify(attrDefined) + "'><i class='fa fa-pencil'></i></a>"
        }
        else {
            //true 不能编辑
            html += "<a href='javascript:;'  onclick='editDimensionAttribute(this,true)' class='btn btn-xs green btn-outline'" +
                " rel='tooltip' title='编辑' data-entiy='" + JSON.stringify(attrDefined) + "'><i class='fa fa-pencil'></i></a>"
            // html += "<a href='javascript:;'  class='btn btn-xs grey btn-outline'" +
            //     " rel='tooltip' title='无法编辑' disabled='disabled'><i class='fa fa-pencil'></i></a>"
        }
        html += "</td></tr>"
        $("#attrDefined").append(html);
    // }
}

//验证属性是否被关联
function checkConnect(id, defIdList) {
    var result = $.grep(defIdList, function (obj) {
        return id == obj;
    });
    return result.length;
}

//已关联过的属性不能再次编辑
function editAttributeUnDisabled(flag) {
    if (flag) {
        $("#cname").attr("disabled", "disabled");
        $("#itemType").attr("disabled", "disabled");
        $("#itemLength").attr("disabled", "disabled");
        $("#hastate").addClass("active");
        $("#nostate").removeClass("active");
        $("#attribute_state").hide();
    }
    else {
        $("#cname").removeAttr("disabled");
        $("#itemType").removeAttr("disabled");
        $("#itemLength").removeAttr("disabled");
        $("#hastate").addClass("active");
        $("#nostate").removeClass("active");
        $("#attribute_state").show();
    }

}

//显示新增或者编辑的浮动框
function showDimension(curr) {
    currentTr = curr;
    $("#dimensionArea").modal('show');
    if (curr == null) {
        currentDimensionId = null;
        $("#dimensionStatus").hide();
        $(".modal-title").html("新增维度");
        $("#dimensionCode").val("");
        $("#dimensionCode").removeAttr("disabled");
        $("#dimensionDesc").val("");
        $("#dimensionName").val("");
    } else {

        $("#dimensionStatus").show();
        $(".modal-title").html("编辑维度");
        $("#valid").removeClass("active");
        $("#inValid").removeClass("active");

        var dimension = $(curr).data("dimension");
        console.log(dimension);

        if (dimension.status) {
            $("#valid").addClass("active");
        } else {
            $("#inValid").addClass("active");
        }

        $("#dimensionCode").val(dimension.classDimensionCode);
        $("#dimensionCode").attr("disabled", true);
        $("#dimensionDesc").val(dimension.classDimensionDesc);
        $("#dimensionName").val(dimension.classDimensionName);
        currentDimensionId = dimension.id;
        console.log(currentDimensionId);
    }
}

//添加或更新维度
function addOrUpdateDimension(dimension) {
    var requestUrl;
    var requestType;
    var requestData = JSON.stringify(dimension);

    if (currentDimensionId == null) {
        requestType = "post";
        requestUrl = webroot + "/dimension/adddimension"
    } else {
        requestType = "put";
        requestUrl = webroot + "/dimension/updatedimention"
    }

    $.ajax({
        type: requestType,
        url: requestUrl,
        data: requestData,
        contentType: "application/json;charset=utf-8",
        success: function (response) {
            if (response.code == 200) {
                var dimension = response.data;
                console.log(dimension);
                swal({
                        //成功，确定
                        title: ICICLELangUtil.getText("", 1005),
                        text: "",
                        type: "success",
                        showCancelButton: false,
                        confirmButtonClass: "btn-success",
                        confirmButtonText: ICICLELangUtil.getText("", 1007),
                        closeOnConfirm: true
                    },

                    function () {
                        location.reload();
                    }
                );
            }
            else {
                //失败
                swal(ICICLELangUtil.getText("", 1148), response.message, "error");
            }
        },
        error: function () {
            //发送失败
            //网络中断
            swal(ICICLELangUtil.getText("", 1163), ICICLELangUtil.getText("", 1064), "error");
        }
    });
}


//添加或更新属性定义
function addOrUpdateDefined(curr) {
    $("#attributeDefinedArea").modal('show');
}

function showOrHideItem(flag) {
    //有选项
    if (flag) {
        $("#dataSelectTable").show();
        $("#itemcontent").show();
        $("#itemStatus").hide();
        $("#itemName").removeAttr("disabled");
        //$("#itemTable").val(0);
    }
    else {
        $("#itemcontent").hide();
        $("#itemStatus").hide();
        $("#dataSelectTable").hide();
        $("#itemTable").val(0);
    }
}

function showOrHideSelect(flag) {
    //选下拉框
    if (flag) {
        $("#dataSelectTable").show();
        $("#itemcontent").hide();
        $("#itemStatus").hide();
        $("#itemName").removeAttr("disabled");
    }
    else {
        $("#dataSelectTable").hide();
        $("#itemcontent").show();
        $("#itemStatus").hide();
        $("#itemName").removeAttr("disabled");
    }
}

//绘制选项表格
function drawItem() {
    for (var i = 0; i < itemList.length; i++) {
        var item = itemList[i];
        var html = "<tr><td class=\"text-center\">" + item.name + "</td>";
        if (item.status == 1)
            html += "<td class=\"text-center\">有效</td>";
        else
            html += "<td class=\"text-center\">无效</td>";
        html += "<td class=\"text-center\"><a href='javascript:;' class='btn btn-xs green btn-outline' data-index='" + i + "' data-entiy='" + JSON.stringify(item) + "' rel='tooltip' title='编辑' onclick='updateItem(this)'><i class='fa fa-pencil'></i></a></td></tr>";
        $("#edititem").append(html);
    }
}

//单条添加选项
function addItem(itemName) {
    var index = itemList.length - 1;
    var html = "<tr><td class=\"text-center\">" + itemName + "</td>";
    html += "<td class=\"text-center\">有效</td>";
    html += "<td class=\"text-center\"><a href='javascript:;' class='btn btn-xs green btn-outline' data-index='" + index + "' rel='tooltip' title='删除' onclick='deleteItem(this)'><i class='fa fa-remove'></i></a></td></tr>";
    $("#edititem").append(html);

}

//删除选项
function deleteItem(curr) {
    itemList.splice($(curr).data("index"), 1);
    $(curr).parent().parent().remove();
}

//更新已有选项
function updateItem(curr) {
    //修改
    $("#btnAddAttribute").html(ICICLELangUtil.getText("", 1252));
    $("#itemStatus").show();
    var entiy = $(curr).data(entiy);
    $("#itemName").val(entiy.entiy.name);
    $("#itemId").val(entiy.entiy.id);
    $("#itemStatus_0").removeClass("active");
    $("#itemStatus_1").removeClass("active");
    if (entiy.entiy.status = true) {
        $("#itemStatus_1").addClass("active");
    }
    else {
        $("#itemStatus_0").addClass("active");
    }
}

//编辑属性
function editDimensionAttribute(curr, flag) {
    $("#saveDimensionAttrib").val("1");
    //编辑维度属性
    $(".modal-title").html(ICICLELangUtil.getText("", 1507));
    var data = $(curr).data("entiy");
    $("#id").val(data.id);
    $("#acode").val(data.defCode);
    $("#acode").attr("disabled", "disabled");
    $("#cname").val(data.defName);
    $("#itemType").val(data.defType);
    $("#itemLength").val(data.defLength);
    $("#defdesc").val(data.defDesc);
    $("#defGroup").val(data.defGroup);

    if (data.status == 1) {
        $("#hastate").addClass("active");
        $("#nostate").removeClass("active");
    } else {
        $("#hastate").removeClass("active");
        $("#nostate").addClass("active");
    }
    // 多选
    if (data.hasItem == 2) {
        showActive("haveMoreSelect", "noSelect", "haveSelect");
        $("#itemStatus").hide();
        $("#itemName").removeAttr("disabled")
        showOrHideItem(true);

    }
    //单选
    else if (data.hasItem == 1) {
        var vs = $('#itemType  option:selected').val();
        if (data.defType == "bit") {
            showOrHideItem(false);
        }
        else {
            showOrHideItem(true);
        }
        showActive("haveSelect", "noSelect", "haveMoreSelect");
    }
    //无选项
    else {
        showActive("noSelect", "haveSelect", "haveMoreSelect");
        $("#itemcontent").hide();
        showOrHideItem(false);
        $("#edititem").html("");
    }
    if (data.selectTable == null || data.selectTable == "") {
        $("#dataSelectTable").hide();

    }
    else {
        $("#itemTable").val(data.selectTable);
        $("#dataSelectTable").show();
    }
    if (flag) {
        editAttributeUnDisabled(true);
    }
    else {
        editAttributeUnDisabled(false);
    }
    $("#item_select").show();
    $("#attributeDefinedArea").modal('show');
    loadItem(data.id);
}

//更改有选项的状态
function showActive(activePart, unActiveBefore, unActiveAfter) {
    $("#" + activePart).addClass("active");
    $("#" + unActiveBefore).removeClass("active");
    $("#" + unActiveAfter).removeClass("active");
}

//如果有选项从服务器获取下拉选项
function loadItem(id) {
    $("#edititem").html("");
    $.ajax({
        type: "get",
        url: webroot + "/dimension/finddefinedbyId",
        data: {
            id: id
        },
        success: function (response) {
            if (response.code == 200) {
                itemList = response.data;
                drawItem(itemList);
            }
            else {
                $("#itemcontent").hide();
            }
        },
        error: function () {
            //发送失败
            //网络中断
            swal(ICICLELangUtil.getText("", 1163), ICICLELangUtil.getText("", 1064), "error");
        }
    });
}

//维度关联属性
function connectAttribute(cur) {
    listDimensionAttri();
    var dimensionDetail = $(cur).data('dimension');//product_dimension 表

    $("#classDimensionName").val(dimensionDetail.classDimensionName);
    $("#AttributeDimensionCode").val(dimensionDetail.classDimensionCode);
    $("#AttributeDimensionId").val(dimensionDetail.id);
    $("#checklist").html(" ");
    var flag = false;
    for (var i = 0; i < attributeDefinedList.length; i++) {
        var html = "";
        var attributeDefined = attributeDefinedList[i];
        if (attributeDefined.status == 0)
            continue;

        if (!contain(dimensionDetail.id, attributeDefined.id)) {
            flag = true;
            html = "<label class=\"mt-checkbox col-md-6\">" + attributeDefined.defName;
            html += "<input name='checkbox' type=\"checkbox\"  value='" + attributeDefined.id + "' />";
            html += "<span></span></label>";
        }
        $("#checklist").append(html);
    }

    if (!flag) {
        swal("您没有任何属性可以关联", "", "warning");
        $("#connectAttribute").modal('hide');
        return;
    }
    else {
        $("#connectAttribute").modal('show');
    }
    $("#btnConnect").show();
}

//验证是否有关联
function contain(dimensionId, attributeDefId) {
    if (dimensionAttriList != null) {
        for (var i = 0; i < dimensionAttriList.length; i++) {
            var dimensionAttri = dimensionAttriList[i];
            if (dimensionAttri.dimensionId == dimensionId) {
                if (dimensionAttri.attributeId == attributeDefId && dimensionAttri.status == true)
                    return true;
            }
        }
        return false;
    }
    else {
        return false;
    }
};

//发送属性关联请求
function attributeRelevant() {
    var checkList = [];
    $('#checklist label input[type=checkbox]').each(function (i) {
        if ($(this).prop('checked')) {
            checkList.push({
                dimensionId: $("#AttributeDimensionId").val(),
                attributeId: $(this).val()
            });
        }
    });
    if (checkList == null || checkList.length == 0) {
        swal("您还没有关联任何属性", "", "warning");
        return;
    }
    var requestData = {
        "dimensionId": $("#AttributeDimensionId").val(),
        "classDimensionCode": $("#AttributeDimensionCode").val(),
        "productDimensionAttributeList": checkList
    };

    $.ajax({
        type: "POST",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(requestData),
        url: webroot + "/dimension/attribconnect",
        success: function (response) {
            if (response.code == 200) {
                swal({
                        //成功，属性关联成功
                        title: ICICLELangUtil.getText("", 1005),
                        text: ICICLELangUtil.getText("", 1046),
                        type: "success",
                        showCancelButton: false,
                        confirmButtonClass: "btn-success",
                        confirmButtonText: ICICLELangUtil.getText("", 1007),
                        closeOnConfirm: true
                    },
                    function () {
                        location.reload();
                    });
            } else {
                swal(response.message, "", "error");
            }
        },
        beforeSend: function (XMLHttpRequest) {
            $("#btnConnect").attr("disabled", "disabled");
            $('#loading').modal('show');
        },
        complete: function (XMLHttpRequest, textStatus) {
            $("#btnConnect").removeAttr("disabled");
            $('#loading').modal('hide');
        },
        error: function () {
            //程序出现未知错误，请稍候重试
            swal(ICICLELangUtil.getText("",1035), "", "error");
        }
    });
}


//查看企划列表
function viewList(curr) {
    var id = $(curr).data("dimension");
    if (id == 1) {
        location.href = webroot + "/dimension/featurelist";
    }
    if (id == 2) {
        location.href = webroot + "/dimension/merchandlist";
    }

}

// 加载所有表名
function loadTableName() {
    var html = "";
    html = "<option value='" + 0 + "'>请选择</option>";
    for (var i = 0; i < tableNameList.length; i++) {
        var content = tableNameList[i];
        if (content.indexOf("select_uom") >= 0) {
            continue;
        }
        html += "<option value='" + content + "'>" + content + "</option>";

    }
    $("#itemTable").html(html);
}

//下拉选项改变 （针对bit）
function showAllItemTable() {
    var vs = $('#itemType  option:selected').val();
    if (vs == "bit") {
        $("#dataSelectTable").hide();
        showActive("haveSelect", "noSelect", "haveMoreSelect");
        $("#item_select").hide();
        $("#itemcontent").hide();
        $("#itemTable").val(0);
    }
    else {
        $("#item_select").show();
    }
}


//
function listDimensionAttri() {
    $.ajax({
        type: "post",
        url: webroot + "/dimension/listdimensionattri",
        success: function (response) {
            if (response.code == 200) {
                dimensionAttriList = response.data;
            }
        },
        error: function () {
        }
    });
}



