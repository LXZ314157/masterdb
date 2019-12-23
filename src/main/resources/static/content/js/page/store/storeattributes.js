/**
 * Created by liurenhua on 2017/10/19.
 */
var itemList = [];
var storeAttribDefVOList = [];
var storeGroupAttribList = [];
var sotreAttribGroupList = [];
$(function () {
    //加载页面属性
    $.ajax({
        type: "POST",
        contentType: "application/json;charset=utf-8",
        url: webroot + "/store/storedata",
        success: function (response) {
            if (response.code == 200) {
                console.log(response.data);
                storeAttribDefVOList = response.data.storeAttribDefVOList;
                storeGroupAttribList = response.data.storeGroupAttribList;
                sotreAttribGroupList = response.data.sotreAttribGroupList;
                loadAttribDef();
                loadAttribGroup();
            }
        }
    });

    function loadAttribDef() {
        for (var i = 0; i < storeAttribDefVOList.length; i++) {
            var storeAttribDefined = storeAttribDefVOList[i];
            var entiy = commonClass.replaceAllQuotationMarks(JSON.stringify(storeAttribDefined));
            var html = "<tr role=\"row\" class=\"heading\">";
            html += "<td class=\"detail\">" + storeAttribDefined.storeAttribDefName + "</td>";
            html += "<td class=\"detail\">" + storeAttribDefined.storeAttribDefCode + "</td>";

            if (storeAttribDefined.storeAttribNature == 0) {
                html += "<td class=\"detail\">基础属性</td>";
            } else {
                html += "<td class=\"detail\">扩展属性</td>";
            }



            html += "<td class=\"detail\">" + storeAttribDefined.itemType + "</td>";
            html += "<td class=\"detail\">" + storeAttribDefined.itemLength + "</td>";
            if (storeAttribDefined.hasItem) {
                html += "<td class=\"detail\">✔</td>";
            } else {
                html += "<td class=\"detail\">✘</td>";
            }
            html += "<td class=\"detail\">" + storeAttribDefined.defineOrder + "</td>";
            if (storeAttribDefined.status == 1) {
                html += "<td class=\"detail\">有效</td>";
            } else {
                html += "<td class=\"detail\">无效</td>";
            }
            html += "<td class=\"detail text-center\">";
            html += "<a href='javascript:;'  class='btn btn-xs green btn-outline' rel='tooltip' title='编辑' data-entiy='" + entiy + "'  onclick='editStoreAttribute(this)'><i class='fa fa-pencil'></i></a></td>";
            $("#storeAttributeDef").append(html);
        }
    }

    function loadAttribGroup() {
        for (var i = 0; i < sotreAttribGroupList.length; i++) {
            var storeAttribute = sotreAttribGroupList[i];
            var entiy = commonClass.replaceAllQuotationMarks(JSON.stringify(storeAttribute));
            var html = "<tr role='row' class='heading text-center'>";
            html += "<td class='detail' width='5%'>" + storeAttribute.storeAttribGroupId + "</td>";
            html += "<td class='detail'>" + storeAttribute.storeAttribGroupCode + "</td>";
            html += "<td class='detail'>" + storeAttribute.storeAttribGroupName + "</td>";
            html += "<td class='text-center'><a href='javascript:;' data-id='" + storeAttribute.storeAttribGroupId + "' class='btn btn-xs red btn-outline' rel='tooltip' title='删除' onclick='deleteAttributeGroup(this)'><i class='fa fa-remove'></i></a>";
            html += "<a href='javascript:;'  class='btn btn-xs green btn-outline' rel='tooltip' title='属性关联' data-entiy='" + entiy + "'  onclick='connectAttribute(this)'><i class='fa fa-adjust'></i></a></td></tr>";
            $("#attributeGroupTable").append(html);
        }
    }


    $("#addAttribute").click(function () {
        itemList = [];
        $("#groupTitle").html("新增属性定义");
        $("#itemStatus").hide();
        $("#saveStoreAttrib").val("0");
        $("#addStoreAttr").modal('show');
        $("#btnAddAttribute").html("保存");
        $("#acode").val("");
        $("#acode").removeAttr("disabled");
        $("#cname").val("");
        $("#itemLength").val("");
        $('#itemcontent').hide();
        $("#edititem").html("");
        $("#hastate").addClass("active");
        $("#noSelect").addClass("active");
        $("#itemCode").val("");
        $("#defineOrder").val("");
        $("#itemName").val("");
        $("#itemDesc").val("");
        $("#isSycn").val("0");
        $("#itemCode").removeAttr("disabled")
    });
    $("#addAttributeGroup").click(function () {
        $("#groupcode").val("");
        $("#groupname").val("");
        $("#supersearch").modal('show');
    });


    $('#savegroup').click(function () {
        var gname = commonClass.replaceAllQuotationMarks($('#groupname').val());
        var gcode = commonClass.replaceAllQuotationMarks($('#groupcode').val());

        if (gname.length <= 0 || gcode.length <= 0) {
            swal("失败!", "请输入【属性组编码】【属性组名称】", "warning");
        } else if (!commonClass.checkCode(gcode)) {
            swal("警告", "编码只能为英文和下划线的组合", "warning");
            return;
        }
        else {
            $.ajax({
                type: "get",
                url: webroot + "/store/add",
                data: {'gname': gname, 'gcode': gcode},
                success: function (data) {
                    if (data.code == 200) {
                        $("#supersearch").modal('hide');
                        $('#groupname').val("");
                        $('#groupcode').val("");
                        swal({
                                title: "属性添加成功！",
                                text: "",
                                type: "success",
                                showCancelButton: false,
                                confirmButtonClass: "btn-success",
                                confirmButtonText: "确定",
                                closeOnConfirm: true
                            },
                            function () {
                                location.reload();
                            });
                    }
                    else {
                        swal("发送失败!", data.message, "error");
                    }
                },
                error: function () {
                    swal("发送失败!", "网络连接断开，请稍后再试", "error");
                },
                beforeSend: function (XMLHttpRequest) {
                    $("#savegroup").attr("disabled", "disabled");
                    $('#loading').modal('show');
                },
                complete: function (XMLHttpRequest, textStatus) {
                    $("#savegroup").removeAttr("disabled");
                    $('#loading').modal('hide');
                }
            });
        }
    });

    $(".connectAttribute").click(function () {
        $("#connect").modal('show');
        $.ajax({
            type: "POST",
            url: webroot + "/store/add",
            data: {'gname': gname, 'gcode': gcode},
            success: function (data) {
                if (data.code == 200) {
                    $("#supersearch").modal('hide');
                    $('#groupname').val("");
                    $('#groupcode').val("");
                    swal({
                            title: "属性组添加成功！",
                            text: "",
                            type: "success",
                            showCancelButton: false,
                            confirmButtonClass: "btn-success",
                            confirmButtonText: "确定",
                            closeOnConfirm: true
                        },
                        function () {
                            location.reload();
                        });
                }
                else {
                    swal("发送失败!", data.message, "error");
                }
            },
            error: function () {
                swal("发送失败!", "网络连接断开，请稍后再试", "error");
            }
        });
    })

    $(".edit").click(function () {
        $("#editStoreAttribute").modal('show');
    })
    //控制开关控件
    //加载网页网页请求数据
    var attributeGroup = false;
    var attribute = false;

    $(".has_item").click(function () {
        $('#itemcontent').show();
    });
    $(".no_item").click(function () {
        $('#itemcontent').hide();
    });

    //保存店铺属性
    $("#saveStoreAttrib").click(function () {
        var itemType = $("#itemType").val();
        var itemLength = $("#itemLength").val();
        if ($("#acode").val().length == 0 || $("#cname").val().trim().length == 0 ||
            itemType.length == 0) {
            swal("警告", "信息没有填写完全，无法提交", "warning");
            return;
        }

        if ((itemType == "Varchar" || itemType == "Nvarchar")
            && itemLength.length == 0) {
            swal("警告", "您必须输入选项长度", "warning");
            return;
        }
        if (itemType == "Money" || itemType == "Datetime" || itemType == "Decimal" ||
            itemType == "Float" || itemType == "Int") {
            if ($('#itemLength').val().length == 0){
                $('#itemLength').val(0);
            }
        }

        if (!commonClass.checkCode($("#acode").val())) {
            swal("警告", "编码只能为英文字母和下划线的组合", "warning");
            return;
        }
        //^(0|[1-9][0-9]*)$
        if (!/^([0-9]*)$/.test($("#itemLength").val())) {
            swal("警告", "长度必须是整形数字", "warning");
            return;
        }
        if (!/^([0-9]*)$/.test($("#defineOrder").val())) {
            swal("警告", "排序必须是整形数字", "warning");
            return;
        }
        if ($('#defineOrder').val().length == 0){
            $('#defineOrder').val(1);
        }

        var hasItem = $('#state .active input').val() == 1;

        if (hasItem && itemList.length == 0) {
            swal("警告", "选择有选项必须添加选项", "warning");
            return;
        }

        if ($(this).val() == 0) {
            addAttributeDef();
        } else {
            updateAttributeDef();
        }

    });

    $("#btnAddAttribute").click(function () {

        if ($("#itemName").val().length == 0
            || $("#itemCode").val().length == 0) {
            swal("警告", "选项编码或选项名不能为空", "warning");
            return;
        }

        if ($(this).html() == "修改") {
            $("#edititem").html("");
            for (var i = 0; i < itemList.length; i++) {
                if (itemList[i].storeAttribItemId == $("#itemId").val()) {
                    itemList[i].storeAttribItemName = $("#itemName").val();
                    itemList[i].storeAttribItemDesc = $("#itemDesc").val();
                    itemList[i].status = $("#itemStatus .active input").val();
                }
            }
            drawItem();
            $("#itemName").val("");
            $("#itemCode").val("");
            $("#itemDesc").val("");
            $("#itemCode").removeAttr("disabled");
            $(this).html("保存");
            $("#haveSelect").addClass("active");
        } else {
            for (var i = 0; i < itemList.length; i++) {
                if (itemList[i].storeAttribItemCode == $("#itemCode").val()) {
                    swal("警告!", "当前编码已存在，请换个编码", "warning");
                    return;
                }
            }
            itemList.push({
                status: $("#itemStatus .active input").val(),
                storeAttribItemId: $("#itemId").val(),
                storeAttribItemCode: $("#itemCode").val(),
                storeAttribItemName: $("#itemName").val(),
                storeAttribItemDesc: $("#itemDesc").val()
            });
            addItem($("#itemCode").val(), $("#itemName").val(), $("#itemDesc").val());

            $("#itemCode").val("");
            $("#itemName").val("");
            $("#itemDesc").val("");
        }
        $("#itemId").val(0);
    });

    function drawItem() {
        for (var i = 0; i < itemList.length; i++) {
            var item = itemList[i];
            var html = "<tr><td class=\"text-center\">" + item.storeAttribItemCode + "</td>";
            html += "<td class=\"text-center\">" + item.storeAttribItemName + "</td>";
            html += "<td class=\"text-center\">" + item.storeAttribItemDesc + "</td>";
            if (item.status == 1)
                html += "<td class=\"text-center\">有效</td>";
            else
                html += "<td class=\"text-center\">无效</td>";
            html += "<td class=\"text-center\"><a href='javascript:;' class='btn btn-xs green btn-outline' data-index='" + i + "' data-entiy='" + JSON.stringify(item) + "' rel='tooltip' title='编辑' onclick='updateItem(this)'><i class='fa fa-pencil'></i></a></td></tr>";
            $("#edititem").append(html);
        }
    }

    function addItem(itemCode, itemName, itemDesc) {
        var index = itemList.length - 1;
        var html = "<tr><td class=\"text-center\">" + itemCode + "</td>";
        html += "<td class=\"text-center\">" + itemName + "</td>";
        html += "<td class=\"text-center\">" + itemDesc + "</td>";
        html += "<td class=\"text-center\">有效</td>";
        html += "<td class=\"text-center\"><a href='javascript:;' class='btn btn-xs green btn-outline' data-index='" + index + "' rel='tooltip' title='删除' onclick='deleteItem(this)'><i class='fa fa-remove'></i></a></td></tr>";
        $("#edititem").append(html);

    }

});


function deleteAttributeGroup(curr) {
    var storeattributeid = $(curr).data('id');
    swal({
            title: "确定删除该属性组吗?",
            text: "",
            type: "warning",
            showCancelButton: true,
            cancelButtonText: "取消",
            confirmButtonClass: "btn-danger",
            confirmButtonText: "确定",
            closeOnConfirm: false
        },
        function () {
            $.ajax({
                type: "DELETE",
                url: webroot + "/store/deletestoreattributegroup/" + storeattributeid,
                success: function (response) {
                    if (response.code == 200) {
                        swal({
                                title: "删除成功",
                                text: "",
                                type: "success",
                                showCancelButton: false,
                                confirmButtonClass: "btn-success",
                                confirmButtonText: "确定",
                                closeOnConfirm: true
                            },
                            function () {
                                location.reload();
                            });
                    }
                    else {
                        swal("删除失败!", response.message, "error");
                    }
                },
                error: function () {
                    swal("发送失败!", "网络连接断开，请稍后再试", "error");
                }
            });
        });
}

function editStoreAttribute(curr) {
    $("#itemCode").val("");
    $("#itemName").val("");
    $("#itemDesc").val("");

    $("#groupTitle").html("编辑属性组定义");
    $("#saveStoreAttrib").val("1");
    var data = $(curr).data("entiy");
    $("#storeAttribDefId").val(data.storeAttribDefId);

    $("#itemType").val(data.itemType);
    if(data.sycn){
        $("#isSycn").val("1");
    }else{
        $("#isSycn").val("0");
    }
    $("#storeAttribNature").val(data.storeAttribNature);

    if (data.status == 1) {
        $("#hastate").addClass("active");
        $("#nostate").removeClass("active");
    } else {
        $("#hastate").removeClass("active");
        $("#nostate").addClass("active");
    }
    if (data.hasItem) {
        $("#haveSelect").addClass("active");
        $("#noSelect").removeClass("active");
    } else {
        $("#haveSelect").removeClass("active");
        $("#noSelect").addClass("active");
    }

    //加载属性
    $.ajax({
        type: "get",
        contentType: "application/json;charset=utf-8",
        url: webroot + "/store/findattribute",
        data: {
            "storeAttribDefiId": data.storeAttribDefId
        },
        success: function (response) {
            if (response.code == 200) {
                var storeAttribDefined = response.data;
                itemList = response.data.itemList;
                $("#acode").val(storeAttribDefined.storeAttribDefCode);
                $("#acode").attr("disabled", "disabled");
                $("#cname").val(storeAttribDefined.storeAttribDefName);
                $("#itemLength").val(storeAttribDefined.itemLength);
                $("#defineOrder").val(storeAttribDefined.defineOrder);

                if (storeAttribDefined.hasItem) {
                    $("#itemcontent").show();
                } else {
                    $("#itemcontent").hide();
                }
                $("#edititem").html("");
                for (var i = 0; i < itemList.length; i++) {
                    var item = itemList[i];
                    var entiy = JSON.stringify(item);
                    var html = "<tr><td class=\"text-center\">" + item.storeAttribItemCode + "</td>";
                    html += "<td class=\"text-center\">" + item.storeAttribItemName + "</td>";
                    html += "<td class=\"text-center\">" + item.storeAttribItemDesc + "</td>";
                    if (item.status == 1) {
                        html += "<td class=\"text-center\">有效</td>";
                    } else {
                        html += "<td class=\"text-center\">无效</td>";
                    }
                    html += "<td class=\"text-center\"><a href='javascript:;' class='btn btn-xs green btn-outline' data-entiy='" + entiy + "'  data-index='" + i + "' rel='tooltip' title='编辑' onclick='updateItem(this)'><i class='fa fa-pencil'></i></a></td></tr>";
                    $("#edititem").append(html);
                }
            } else {
                swal("失败", response.message, "error");
            }
        },
        error: function () {
            swal("网络中断!", "获取信息失败", "error");
        }
    });
    $("#addStoreAttr").modal('show');
    $("#btnAddAttribute").html("保存");
}

function connectAttribute(cur) {
    $("#connectAttribute").modal('show');
    var storeattribute = $(cur).data('entiy');
    $("#storeAttributeGroupName").val(storeattribute.storeAttribGroupName);
    $("#storeAttributeGroupId").val(storeattribute.storeAttribGroupId);
    $.ajax({
        type: "GET",
        url: webroot + "/store/attributedefine",
        success: function (response) {
            if (response.code == 200) {
                $("#checklist").html(" ");
                var storeAttribDefinedList = response.data;
                for (var i = 0; i < storeAttribDefinedList.length; i++) {
                    var storeAttribDefined = storeAttribDefinedList[i];
                    if (storeAttribDefined.status == 0)
                        continue;
                    var html = "<label class=\"mt-checkbox\">" + storeAttribDefined.storeAttribDefName;
                    if (contain(storeAttribDefined.storeAttribDefId, storeattribute.storeAttribGroupId)) {
                        html += "<input name='checkbox' type=\"checkbox\"  checked value='" + storeAttribDefined.storeAttribDefId + "' />";
                    } else {
                        html += "<input name='checkbox' type=\"checkbox\"  value='" + storeAttribDefined.storeAttribDefId + "' />";
                    }
                    html += "<span></span></label>";
                    $("#checklist").append(html);
                }
            }
            else {
                swal("删除失败!", response.message, "error");
            }
        },
        error: function () {
            swal("失败", "网络连接断开，请稍后再试", "error");
        }
    });
}

function contain(storeAttribDefId, storeAttribGroupId) {
    for (var i = 0; i < storeGroupAttribList.length; i++) {
        var attributeGroup = storeGroupAttribList[i];
        if (attributeGroup.storeAttribGroupId == storeAttribGroupId) {
            if (attributeGroup.storeAttribDefId == storeAttribDefId)
                return true;
        }
    }
    return false;
};

//发送属性关联请求
function attributeRelevant() {
    var checkList = [];
    $('#checklist label input[type=checkbox]').each(function (i) {
        if ($(this).prop('checked')) {
            checkList.push({
                storeAttribGroupId: $("#storeAttributeGroupId").val(),
                storeAttribDefId: $(this).val(),
                status: 1
            });
        }
    });

    var requestData = {
        "storeAttribGroupId": $("#storeAttributeGroupId").val(),
        "storeGroupAttribList": checkList,
    };

    $.ajax({
        type: "POST",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(requestData),
        url: webroot + "/store/attributeconnect",
        success: function (response) {
            if (response.code == 200) {
                swal({
                        title: "成功！",
                        text: "属性关联成功",
                        type: "success",
                        showCancelButton: false,
                        confirmButtonClass: "btn-success",
                        confirmButtonText: "确定",
                        closeOnConfirm: true
                    },
                    function () {
                        location.reload();
                    });
            } else {
                swal("失败!", response.message, "error");
            }
        },
        beforeSend: function (XMLHttpRequest) {
            $("#btnConnect").attr("disabled", "disabled");
            $('#loading').modal('show');
        },
        complete: function (XMLHttpRequest, textStatus) {
            $("#btnConnect").removeAttr("disabled");
            $('#loading').modal('hide');
        }
    });
}

function deleteItem(curr) {
    itemList.splice($(curr).data("index"), 1);
    $(curr).parent().parent().remove();
}

function addAttributeDef() {
    var status = $('#status .active input').val();
    var hasItem = $('#state .active input').val() == 1 ? true : false;
    var defCode = commonClass.replaceAllQuotationMarks($("#acode").val());
    var defName = commonClass.replaceAllQuotationMarks($("#cname").val());
    var defineOrder = commonClass.replaceAllQuotationMarks($("#defineOrder").val());
    var requestData = {
        "storeAttribDefCode": $.trim(defCode),
        "storeAttribDefName": $.trim(defName),
        "itemType": commonClass.replaceAllQuotationMarks($("#itemType").val()),
        "isSycn": $("#isSycn").val(),
        "storeAttribNature": "1",
        "itemLength": commonClass.replaceAllQuotationMarks($("#itemLength").val()),
        "hasItem": hasItem,
        "status": status,
        "defineOrder": defineOrder,
        "itemList": itemList
    };
    $.ajax({
        type: "POST",
        url: webroot + "/store/addattributedefine",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(requestData),
        success: function (data) {
            if (data.code == 200) {
                swal({
                        title: "成功！",
                        text: "添加成功",
                        type: "success",
                        showCancelButton: false,
                        confirmButtonClass: "btn-success",
                        confirmButtonText: "确定",
                        closeOnConfirm: true
                    },
                    function () {
                        location.reload();
                    });
            } else {
                swal("失败!", data.message, "error");
            }
        },
        error: function () {
            swal("发送失败!", "网络中断", "error");
        },
        beforeSend: function (XMLHttpRequest) {
            $("#saveStoreAttrib").attr("disabled", "disabled");
            $('#loading').modal('show');
        },
        complete: function (XMLHttpRequest, textStatus) {
            $("#saveStoreAttrib").removeAttr("disabled");
            $('#loading').modal('hide');
        }
    });
}

//更新店铺属性
function updateAttributeDef() {
    var status;
    // if (!$('#state input[name="nameitem"]:checked'))
    status = $('#status .active input').val();
    console.log(status);
    var hasItem = $('#state .active input').val() == 1;

    var requestData = {
        "storeAttribDefId": commonClass.replaceAllQuotationMarks($("#storeAttribDefId").val()),
        "storeAttribDefCode": commonClass.replaceAllQuotationMarks($("#acode").val()),
        "storeAttribDefName": commonClass.replaceAllQuotationMarks($("#cname").val()),
        "itemType": commonClass.replaceAllQuotationMarks($("#itemType").val()),
        "isSycn": commonClass.replaceAllQuotationMarks($("#isSycn").val()),
        "itemLength": commonClass.replaceAllQuotationMarks($("#itemLength").val()),
        "defineOrder": commonClass.replaceAllQuotationMarks($("#defineOrder").val()),
        "hasItem": hasItem,
        "status": status,
        "itemList": itemList
    };
    $.ajax({
        type: "POST",
        contentType: "application/json;charset=utf-8",
        url: webroot + "/store/updatestoreattribdef",
        data: JSON.stringify(requestData),
        success: function (response) {
            if (response.code == 200) {
                swal({
                        title: "成功！",
                        text: "更新成功",
                        type: "success",
                        showCancelButton: false,
                        confirmButtonClass: "btn-success",
                        confirmButtonText: "确定",
                        closeOnConfirm: true
                    },
                    function () {
                        location.reload();
                    });
            } else {
                swal("失败", response.message, "error");
            }
        },
        error: function () {
            swal("发送失败!", "网络中断", "error");
        },
        beforeSend: function (XMLHttpRequest) {
            $("#saveStoreAttrib").attr("disabled", "disabled");
            $('#loading').modal('show');
        },
        complete: function (XMLHttpRequest, textStatus) {
            $("#saveStoreAttrib").removeAttr("disabled");
            $('#loading').modal('hide');
        }
    });
}
function updateItem(curr) {

    $("#btnAddAttribute").html("修改");
    $("#itemStatus").show();
    var entiy = $(curr).data(entiy);

    $("#itemCode").val(entiy.entiy.storeAttribItemCode);
    $("#itemCode").attr("disabled", "true");
    $("#itemName").val(entiy.entiy.storeAttribItemName);
    $("#itemDesc").val(entiy.entiy.storeAttribItemDesc);
    $("#itemId").val(entiy.entiy.storeAttribItemId);

    $("#itemStatus_0").removeClass("active");
    $("#itemStatus_1").removeClass("active");
    $("#itemStatus_" + entiy.entiy.status).addClass("active");

    $("#itemStatus").find("label")[entiy.status].addClass("active");
    console.log($("#itemId").val());
}