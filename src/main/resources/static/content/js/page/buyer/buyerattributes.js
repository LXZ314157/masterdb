var itemlist = [];
var buyerAttribDefVOList = [];
var buyerAttribDefVOValidList = [];
var buyerAttribDefVOInvalidList = [];
var buyerAttribDefVOList = [];
var buyerGroupAttribList = [];
var buyerAttribGroupList = [];
$(document).ready(function () {
    //加载页面属性
    $.ajax({
        type: "POST",
        contentType: "application/json;charset=utf-8",
        url: webroot + "/buyer/buyershareddata",
        success: function (response) {
            if (response.code == 200) {
                buyerAttribDefVOList = response.data.buyerAttribDefVOList;
                buyerGroupAttribList = response.data.buyerGroupAttribList;
                buyerAttribGroupList = response.data.buyerAttribGroupList;
                loadGroup();
                loadAttribDef();
            }
            else {
                //数据获取失败，请稍后重试
                swal(ICICLELangUtil.getText("", 1058), "", "warning");
            }
        }

    });
    //添加代理商模块开始
    $("#addAttribute").click(function () {
        //添加代理商属性
        $(".modal-title").html(ICICLELangUtil.getText("", 1490));
        $('#AddAtribute').modal('show');
        $("#acode").removeAttr("disabled");
        $('#acode').val("");
        $('#cname').val("");
        $('#defineOrder').val("");
        $('#isSycn').val("0");
        $('#attribute_state').hide();
        $('#itemcontent').hide();
        $('#buyerValue').val(0);
        $('#way').val("");
        $('#len').val("");
        $('#iname').val("");
        $('#itemState').hide();
        $('#edititem').html("");
    });
    $(".has_item").click(function () {
        $('#itemcontent').show();
        $('#itemState').hide();
    });
    $(".no_item").click(function () {
        $('#itemcontent').hide();
    });
    $(".end").click(function () {
        $("#icode").val("");
        $("#iname").val("");
        $("#groupcode").val("");
        $("#groupname").val("");
        $('#icode').removeAttr("disabled");
        itemlist.splice(0, itemlist.length);
    })
    $(".close").click(function () {
        $("#icode").val("");
        $("#iname").val("");
        $("#groupcode").val("");
        $("#groupname").val("");
        $('#icode').removeAttr("disabled");
        itemlist.splice(0, itemlist.length);
        ;
    })
    $('#savetype').click(function () {
        var id = $('#buyerValue').val();
        var tid = $('#save_attribute').data('tid');
        var cname = commonClass.replaceAllQuotationMarks($('#cname').val());
        var acode = $.trim(commonClass.replaceAllQuotationMarks($('#acode').val()));
        var defineOrder = $.trim(commonClass.replaceAllQuotationMarks($('#defineOrder').val()));
        var isSycn = $.trim(commonClass.replaceAllQuotationMarks($('#isSycn').val()));
        var way = $('#way').val();
        if ($("#len").val().length == 0) {
            $("#len").val(0);
        }
        var len = $('#len').val();
        var state = $('#state input[name="rstate"]:checked').val();
        var item = $('#sitem .active input').val();//有选项
        var itemid = $('#itemValue').val();
        var data = [];
        var requestType = "";
        var url = "";
        if (cname == "" || acode == "") {
            //请按格式正确填写属性名,属性编码
            swal(ICICLELangUtil.getText("", 1002), "", "error");
            return;
        }
        if (!commonClass.checkCode(acode)) {
            swal("编码只能为字母和下划线组合", "", "warning");
            return;
        }
        if (way == "") {
            //请填写属性类型
            swal(ICICLELangUtil.getText("", 1003), "", "error");
            return;
        }
        if (typeof(item) == "undefined") {
            //请选择有值选项
            swal(ICICLELangUtil.getText("", 1031), "", "error");
            return;
        }
        if (!commonClass.checkInt(len)) {
            //值长度必须为整数
            swal("值长度必须为整数", "", "warning");
            return;
        }
        if (defineOrder.length == 0){
            $('#defineOrder').val(1);
        }
        if (!commonClass.checkInt( $('#defineOrder').val())) {
            //值长度必须为整数
            swal("排序值必须为整数", "", "warning");
            return;
        }

        if (id == 0 || id == "") {
            if (item == 1) {
                if (itemlist == null || itemlist.length == 0) {
                    //有选项需添加相应内容
                    swal(ICICLELangUtil.getText("", 1009), "", "warning");
                    return;
                }
            }
            if (item == 1 && itemlist != null && itemlist.length != 0) {//有选项
                data = {
                    'buyerAttribDefCode': acode,
                    'buyerAttribDefName': cname,
                    'itemType': way,
                    'hasItem': item,
                    'itemLength': len,
                    'defineOrder': $('#defineOrder').val(),
                    'isSycn': isSycn,
                    'list': itemlist
                };
            }
            else {
                data = {
                    'buyerAttribDefCode': acode,
                    'buyerAttribDefName': cname,
                    'itemType': way,
                    'itemLength': len,
                    'defineOrder': $('#defineOrder').val(),
                    'isSycn': isSycn,
                    'hasItem': item
                };
            }
            requestType = "POST";//insert
            url = webroot + "/buyer/attributeinsert";
        }
        else {
            requestType = "PUT";//update
            url = webroot + "/buyer/attributeupdate";
            if (item == 1) {
                if (itemlist == null || itemlist.length == 0) {
                    //有选项需添加相应内容
                    swal(ICICLELangUtil.getText("", 1009), "", "warning");
                    return;
                }
            }
            if (item == 1 && itemlist != null && itemlist.length != 0) {
                data = {
                    'buyerAttribItemId': itemid,
                    'buyerAttribDefId': id,
                    'buyerAttribDefCode': acode,
                    'buyerAttribDefName': cname,
                    'itemType': way,
                    'itemLength': len,
                    'hasItem': item,
                    'status': state,
                    'defineOrder': $('#defineOrder').val(),
                    'isSycn': isSycn,
                    'list': itemlist
                };
            }
            else {
                data = {
                    'buyerAttribItemId': itemid,
                    'buyerAttribDefId': id,
                    'buyerAttribDefName': cname,
                    'buyerAttribDefCode': acode,
                    'itemType': way,
                    'itemLength': len,
                    'hasItem': item,
                    'defineOrder': $('#defineOrder').val(),
                    'isSycn': isSycn,
                    'status': state
                };
            }
        }
        $.ajax({
            type: requestType,
            url: url,
            contentType: "application/json;charset=utf-8",
            data: JSON.stringify(data),
            success: function (msg) {
                if (msg.code == 200) {
                    swal({
                            //成功,确定
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
                        });
                }
                else {
                    swal(msg.message, "", "error");
                }
            },
            error:function(){
                $("#savetype").removeAttr("disabled");
                $('#loading').modal('hide');
                swal("您的网络似乎出现一些问题，请稍后重试","","error");
            },
            beforeSend: function (XMLHttpRequest) {
                $("#savetype").attr("disabled", "disabled");
                $('#loading').modal('show');
            },
            complete: function (XMLHttpRequest, textStatus) {
                $("#savetype").removeAttr("disabled");
                $('#loading').modal('hide');
            }
        })
    });
    $('.btnAddAttribute').click(function () {
        var icode = commonClass.replaceAllQuotationMarks($('#icode').val());
        var iname = commonClass.replaceAllQuotationMarks($('#iname').val());
        var id = $('#itemValue').val();
        var update = $(".btnAddAttribute").val();//标志保存修改
        if (update == 1) {//修改
            $("#edititem").html("");
            for (var i = 0; i < itemlist.length; i++) {
                if (itemlist[i].buyerAttribItemId == $('#itemValue').val()) {
                    itemlist[i].buyerAttribItemName = $('#iname').val();
                    itemlist[i].status = $('#itemState .active input').val();
                }
            }
            drawItem();
            $('#itemValue').val(0);
            $('#iname').val("");
            $("#icode").val("");
            $("#icode").removeAttr("disabled");
            //添加
            $(this).html(ICICLELangUtil.getText("", 1112));
            $(".btnAddAttribute").val(0);
            $('#itemState').hide();
        }
        else {//添加
            var n = $('#itemValue').val();
            var m = $('#icode').val();
            if ($('#icode').val() == "" || $("#iname").val() == "") {
                //请填写选项编码,选项名称
                swal(ICICLELangUtil.getText("", 1015), "", "warning");
                return;
            }
            for (var i = 0; i < itemlist.length; i++) {
                if (itemlist[i].buyerAttribItemCode == $('#icode').val()) {
                    //当前编码已存在，请换个编码
                    swal(ICICLELangUtil.getText("", 1037), "", "warning");
                    return;
                }
            }
            itemlist.push({
                buyerAttribItemCode: commonClass.replaceAllQuotationMarks($("#icode").val()),
                buyerAttribItemName: commonClass.replaceAllQuotationMarks($("#iname").val()),
                status: 1
            });
            addItem($("#icode").val(), $("#iname").val());
            $("#icode").val("");
            $("#iname").val("");
        }
        var id = $('#save_attribute').data('mid');
        $('#itemValue').val(0);
    });
    //属性下方栏编辑
    //属性组开始
    $("#addAttributeGroup").click(function () {
        //添加属性组
        $(".modal-title").html(ICICLELangUtil.getText("", 1116));
        $("#AddGroup").modal('show');
    })
    //添加属性组
    $('#savegroup').click(function () {
        var gname = commonClass.replaceAllQuotationMarks($('#groupname').val());
        var gcode = commonClass.replaceAllQuotationMarks($('#groupcode').val());
        if (gname.length <= 0 || gcode.length <= 0) {
            //请输入属性组编码和属性组名称
            swal(ICICLELangUtil.getText("", 1043), "", "warning");
            return;
        }
        if (!commonClass.checkCode(gcode)) {
            swal("编码只能为字母和下划线组合", "", "warning");
            return;
        }
        else {
            $.ajax({
                type: "POST",
                url: webroot + "/buyer/addattributegroup",
                data: {'buyerAttribGroupName': gname, 'buyerAttribGroupCode': gcode},
                success: function (data) {
                    if (data.code == 200) {
                        $("#supersearch").modal('hide');
                        $('#groupname').val("");
                        $('#groupcode').val("");
                        swal({
                                //属性添加成功,确定
                                title: ICICLELangUtil.getText("", 1044),
                                text: "",
                                type: "success",
                                showCancelButton: false,
                                confirmButtonClass: "btn-success",
                                confirmButtonText: ICICLELangUtil.getText("", 1007),
                                closeOnConfirm: true
                            },
                            function () {
                                location.reload();
                            });
                    }
                    else if (data.code == 400) {
                        //属性组编码添加重复
                        swal(ICICLELangUtil.getText("", 1045), "", "error");
                    }
                    else {
                        //程序出现未知错误，请稍候重试
                        swal(ICICLELangUtil.getText("", 1035), "", "error");
                    }
                },
                error:function(){
                    swal("您的网络似乎出现一些问题，请稍后重试","","error");
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
    //关联属性
    $(".connectAttribute").click(function () {
        $('#attribute_list').html("");
        var id = $(this).val();
        $('#connectValue').val(id);
        var groupname = $(this).data('groupname');
        $('#buyerAttributeGroupId').val(id);
        $('.relationgroupname').val(groupname);
        $('.relationgroupname').attr("disabled", "disabled");
        $('#attribute_list').html("");
        connectload(id);
        $("#connect").modal('show');

    });
});

function connectload(attributeGroupId, id) {
    var html = '';
    for (var i = 0; i < buyerAttribDefVOList.length; i++) {
        var gi = buyerAttribDefVOList[i];
        if (gi.status == 1) {

            var html = "<label class=\"mt-checkbox col-md-6\" style='display: block'>" + gi.buyerAttribDefName;
            if (contain(gi.buyerAttribDefId, attributeGroupId)) {
                html += "<input type=\"checkbox\" checked value='" + gi.buyerAttribDefId + "'/>";
            }
            else {
                html += "<input type=\"checkbox\" value='" + gi.buyerAttribDefId + "'  />";
            }
            html += "<span></span></label>";

            $('#attribute_list').append(html);
        }
    }
    $('#relationAttrib').modal("show");
}

function edititem(brr) {
    var itemid = $(brr).data('itemid');
    var entiy = $(brr).data(entiy);
    $('#itemValue').val(entiy.entiy.buyerAttribItemId);
    $("#icode").val(entiy.entiy.buyerAttribItemCode);
    $("#iname").val(entiy.entiy.buyerAttribItemName);
    //修改
    $(".btnAddAttribute").text(ICICLELangUtil.getText("", 1252));
    $(".btnAddAttribute").val(1);
    $("#icode").attr("disabled", "disabled");
    $('#itemState').show();
    if (entiy.entiy.status == 0) {
        $('#state_no').addClass("active");
    } else {
        $('#state_has').addClass("active");
    }

}

function deleteitem(row) {
    itemlist.splice($(row).data("index"), 1);
    $(row).parent().parent().remove();
    $("#icode").val("");
    $("#iname").val("  ");

}

function deleteGroup(id) {
    $.ajax({
        type: "DELETE",
        url: webroot + "/buyer/deletegroup/" + id,
        success: function (data) {
            if (data.code == 200) {
                location.reload();
            } else {
                //请刷新页面，查看该属性是否存在,删除失败
                swal(ICICLELangUtil.getText("", 1042), "", "error");
            }
        },
        error:function(){
            swal("您的网络似乎出现一些问题，请稍后重试","","error");
        },
        beforeSend: function (XMLHttpRequest) {
            $("#removeGroup").attr("disabled", "disabled");
            $('#loading').modal('show');
        },
        complete: function (XMLHttpRequest, textStatus) {
            $("#removeGroup").removeAttr("disabled");
            $('#loading').modal('hide');
        }
    })


}

function attributeRelevant() {
    var checkList = [];
    $('#attribute_list label input[type=checkbox]').each(function (i) {
        if ($(this).prop('checked')) {
            checkList.push({
                buyerAttribGroupId: $("#buyerAttributeGroupId").val(),
                buyerAttribDefId: $(this).val()
            });
        }
    });
    var requestData = {
        "buyerAttribGroupId": $("#buyerAttributeGroupId").val(),
        "buyerGroupAttribList": checkList
    };
    $.ajax({
        type: "POST",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(requestData),
        url: webroot + "/buyer/attrconnect",
        success: function (response) {
            if (response.code == 200) {
                swal({
                        //"属性关联成功！",确定
                        title: ICICLELangUtil.getText("", 1046),
                        text: "",
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
        }
    });
}

function drawItem() {
    $("#edititem").html("");
    for (var i = 0; i < itemlist.length; i++) {
        var item = itemlist[i];
        var html = "<tr><td class=\"text-center\">" + item.buyerAttribItemCode + "</td>";
        html += "<td class=\"text-center\">" + item.buyerAttribItemName + "</td>";
        if (item.status == 1) {
            html += "<td class=\"text-center\">正常</td>";
        }
        else {
            html += "<td class=\"text-center\">无效</td>";
        }
        html += "<td class=\"text-center\"><a href='javascript:;' class='btn btn-xs green btn-outline' data-index='" + i + "' data-entiy='" + JSON.stringify(item) + "' rel='tooltip' title='编辑' onclick='edititem(this)'><i class='fa fa-pencil'></i></a></td></tr>";
        $("#edititem").append(html);
    }
}

function addItem(itemCode, itemName) {
    var index = itemlist.length - 1;
    var html = "<tr><td class=\"text-center\">" + itemCode + "</td>";
    html += "<td class=\"text-center\">" + itemName + "</td>";
    html += "<td class=\"text-center\">正常</td>";
    html += "<td class=\"text-center\"><a href='javascript:;' class='btn btn-xs green btn-outline' data-index='" + index + "' rel='tooltip' title='删除' onclick='deleteitem(this)'><i class='fa fa-remove'></i></a></td></tr>";
    $("#edititem").append(html);

}

function renderItemById(id) {
    var html = "";
    $.ajax({
        type: "GET",
        url: webroot + "/buyer/itemfindall?buyerAttribDefId=" + id,
        success: function (msg) {
            itemlist = msg.data;
            if (itemlist != null) {
                for (var i = 0; i < itemlist.length; i++) {
                    var item = itemlist[i];
                    var entiy = commonClass.replaceAllQuotationMarks(JSON.stringify(item));
                    var html = "<tr role=\"row\" class=\"heading\">";
                    html += "<td class=\"detail\">" + item.buyerAttribItemCode + "</td>";
                    html += "<td class=\"detail\">" + item.buyerAttribItemName + "</td>";
                    if (item.status == 1) {
                        html += "<td class=\"detail\">正常</td>";
                    }
                    else {
                        html += "<td class=\"detail\">无效</td>";
                    }
                    html += "<td class=\"text-center\"><a href='javascript:;' class='btn btn-xs green btn-outline' data-index='" + i + "' data-entiy='" + JSON.stringify(item) + "' rel='tooltip' title='编辑' onclick='edititem(this)'><i class='fa fa-pencil'></i></a></td></tr>";
                    html += "</td></tr>";
                    $("#edititem").append(html);

                }
            } else {
                itemlist = [];
            }
        }
    });
}

function contain(buyerAttribDefId, buyerAttribGroupId) {
    for (var i = 0; i < buyerGroupAttribList.length; i++) {
        var attributeGroup = buyerGroupAttribList[i];
        if (attributeGroup.buyerAttribGroupId == buyerAttribGroupId) {
            if (attributeGroup.buyerAttribDefId == buyerAttribDefId && attributeGroup.status == true)
                return true;
        }
    }
    return false;
};

function connectAttribute(curr) {
    $('#attribute_list').html("");
    var id = $(curr).data('id');
    $('#connectValue').val(id);
    var groupname = $(curr).data('groupname');
    $('#buyerAttributeGroupId').val(id);
    $('.relationgroupname').val(groupname);
    $('.relationgroupname').attr("disabled", "disabled");
    $('#attribute_list').html("");
    connectload(id);
    $("#connect").modal('show');
}

function deleteAttributeGroup(curr) {
    var id = $(curr).data('id');
    swal({
            //确认删除
            //该属性将不会显示在您的页面中，请谨慎处理,删除,取消
            title: ICICLELangUtil.getText("", 1017),
            text: ICICLELangUtil.getText("", 1039),
            type: "warning",
            showCancelButton: true,
            confirmButtonClass: "btn-success",
            confirmButtonText: ICICLELangUtil.getText("", 1182),
            cancelButtonText: ICICLELangUtil.getText("", 1150),
            closeOnConfirm: true
        },
        function () {
            deleteGroup(id);
        });
}

function loadGroup() {
    if (buyerAttribGroupList.length == 0) {
        //数据获取为空，请稍后重试
        swal(ICICLELangUtil.getText("", 1049), "", "error");
    }
    for (var i = 0; i < buyerAttribGroupList.length; i++) {
        var buyerAttribute = buyerAttribGroupList[i];
        var entiy = commonClass.replaceAllQuotationMarks(JSON.stringify(buyerAttribute));
        if (buyerAttribute.status == 1) {
            var html = "<tr role='row' class='heading text-center'>";
            html += "<td class='detail' width='5%'>" + buyerAttribute.buyerAttribGroupId + "</td>";
            html += "<td class='detail'>" + buyerAttribute.buyerAttribGroupCode + "</td>";
            html += "<td class='detail'>" + buyerAttribute.buyerAttribGroupName + "</td>";
            html += "<td class='text-center'><a href='javascript:;' data-id='" + buyerAttribute.buyerAttribGroupId + "' class='btn btn-xs red btn-outline removeGroup' rel='tooltip' title='删除' onclick='deleteAttributeGroup(this)'><i class='fa fa-remove'></i></a>";
            html += "<a href='javascript:;'  class='btn btn-xs green btn-outline connectAttribute' data-id='" + buyerAttribute.buyerAttribGroupId + "' rel='tooltip' title='属性关联'  data-groupname='" + buyerAttribute.buyerAttribGroupName + "' onclick='connectAttribute(this)'><i class='fa fa-adjust'></i></a></td></tr>";
            $("#attributeGroup").append(html);
        }

    }
}

function editBuyerAttribute(curr) {
    //添加
    $(".btnAddAttribute").html(ICICLELangUtil.getText("", 1112));
    $(".modal-title").html("编辑代理商属性");
    var entiy = $(curr).data('entiy');
    var id = entiy.buyerAttribDefId;
    $('#buyerValue').val(id);
    $('#cname').val(entiy.buyerAttribDefName);
    $('#acode').val(entiy.buyerAttribDefCode);
    $('#defineOrder').val(entiy.defineOrder);
    if(entiy.sycn){
        $('#isSycn').val("1");
    }else{
        $('#isSycn').val("0");
    }

    $("#acode").attr("disabled", "disabled");
    $('#len').val(entiy.itemLength);
    var state = $('#state input[name="rstate"]:checked').val();
    var item = entiy.status;
    var hasitem = entiy.hasItem;
    $('#attribute_state').show();
    $('#hastate').addClass("active");
    if (hasitem == true) {
        $('#itemcontent').show();
        $('#state_no').removeClass("active");
        $('#state_has').removeClass("active");
        $('.has_item').addClass("active");
        $('#itemState').hide();
    }
    else {
        $('#itemcontent').hide();
        $('.no_item').addClass("active");
    }
    $("#icode").val("");
    $("#inamme").val(" ");
    $('#AddAtribute').modal('show');
    $("#edititem").html("");
    $("#way").val(entiy.itemType);
    renderItemById(id);
}

function loadAttribDef() {
    for (var i = 0; i < buyerAttribDefVOList.length; i++) {
        if (buyerAttribDefVOList[i].status == 1) {
            buyerAttribDefVOValidList.push(buyerAttribDefVOList[i]);
        }else{
            buyerAttribDefVOInvalidList.push(buyerAttribDefVOList[i])
        }
    }
    appendAttribDef(buyerAttribDefVOValidList);
    appendAttribDef(buyerAttribDefVOInvalidList);
}


function appendAttribDef(attribDefList){
    for(var i = 0; i < attribDefList.length; i++){
        var buyerAttriDefine = attribDefList[i];
        var entiy = commonClass.replaceAllQuotationMarks(JSON.stringify(buyerAttriDefine));
        var html = "<tr role=\"row\" class=\"heading\">";
        html += "<td class=\"detail text-center\">" + buyerAttriDefine.buyerAttribDefName + "</td>";
        html += "<td class=\"detail text-center\">" + buyerAttriDefine.buyerAttribDefCode + "</td>";
        html += "<td class=\"detail text-center\">" + buyerAttriDefine.itemType + "</td>";
        html += "<td class=\"detail text-center\">" + buyerAttriDefine.itemLength + "</td>";
        if (buyerAttriDefine.hasItem) {
            html += "<td class=\"detail text-center\">✔</td>";
        } else {
            html += "<td class=\"detail text-center\">✘</td>";
        }
        html += "<td class=\"detail text-center\">" + buyerAttriDefine.defineOrder + "</td>";
        if (buyerAttriDefine.status == 1) {
            html += "<td class=\"detail text-center\">有效</td>";
        } else {
            html += "<td class=\"detail text-center\">无效</td>";
        }
        html += "<td class=\"detail text-center\">";
        html += "<a href='javascript:;'  class='btn btn-xs green btn-outline' rel='tooltip' title='编辑' data-entiy='" + entiy + "'  onclick='editBuyerAttribute(this)'><i class='fa fa-pencil'></i></a></td>";
        $("#buyerAttributeDef").append(html);
    }
}
