var mngList = [];
var zoneList = [];
var managerLists = [];
var managerList = [];

$(function () {
    $('#jstree_demo_div').jstree();

    $.ajax({
        type: "GET",
        url: webroot + "/zone/zonelist",
        success: function (response) {
            if (response.code == 200) {
                mngList = response.data.mngList;
                zoneList = response.data.zoneList;
                managerLists = response.data.managerList;
                loadManagerLists();
                buildTree();
            }
            else {
                swal("失败", response.message, "error");
            }
        },
        beforeSend: function (XMLHttpRequest) {
            $("#btnSaveDep").attr("disabled", "disabled");
            $('#loading').modal('show');
        },
        complete: function (XMLHttpRequest, textStatus) {
            $("#btnSaveDep").removeAttr("disabled");
            $('#loading').modal('hide');
        }
    });

    $("#jstree_demo_div").on("mousedown", "a", function () {
        var attrId = $(this).attr("id");
        var attrList = attrId.split("_");
        var type = attrList[0];
        var id = attrList[1];
        var data = [];
        if (type === "mng") {
            data = mngMenu;
        }
        else if (type === "dep") {
            data = depMenu;
        }
        else if (type === "pazone") {
            data = paZoneMenu;
        }
        else {
            data = zoneMenu;
        }
        $.smartMenu.remove();
        $(this).smartMenu(data);
        $('#jstree_demo_div').jstree().deselect_all();
        $('#jstree_demo_div').jstree().select_node(attrId);
    });

    $('.btn-toggle-status').children("label").each(function (i) {
        $(this).click(function () {
            $($(this).parent().children('input')[0]).val($(this).data("val"));
        });
    });

    $('.btnAddBU').click(function () {
        $("#mngId").val("0");
        hideRightArea();
        loadManagerLists();
        $('#mngStatusArea').show();
        $('#mng_title').text("新增现场管理中心");
        $('#manageCenterName').val('');
        $('#staffNum').val('');
        $('#managerName').val('');
        $('#mng_block').show();
    });

    //新增现场管理中心
    $('#btnSaveMng').click(function () {
        var mngId = $('#mngId').val();
        var manageCenterName = commonClass.replaceAllQuotationMarks($('#manageCenterName').val());
        var staffNum = $('#manager').val() == 0?"":commonClass.replaceAllQuotationMarks($('#manager').val());
        var managerName = staffNum == 0?"":$("#manager option:selected").text();
        var manageCenterState = $("input[name=manageCenterState]:checked").val();
        if (manageCenterName == null || manageCenterName == "") {
            swal("现场中心名称不能为空", "", "warning");
            return;
        }
        if(!manageCenterState){
            swal("请选择管理中心状态", "", "warning");
            return;
        }
        var requestType = "";
        var requestUrl = "";
        var data = {};
        if (mngId == 0) {
            requestType = "POST";
            requestUrl = webroot + "/zone/addmng";
            data = {"managerCenterName": manageCenterName, "staffNum": staffNum,"managerName": managerName, "manageCenterState": manageCenterState};
        }
        else {
            var managerCenterId = $("#managerCenterId").val();
            requestType = "PUT";
            requestUrl = webroot + "/zone/updatemng";
            data = {"managerCenterName": manageCenterName, "staffNum": staffNum,"managerName": managerName, "manageCenterState": manageCenterState,"managerCenterId":managerCenterId};
            var status = $('#hidBuState').val()
        }

        $.ajax({
            type: requestType,
            url: requestUrl,
            contentType: "application/json;charset=utf-8",
            data: JSON.stringify(data),
            success: function (response) {
                if (response.code == 200) {
                    $('#area').modal('hide');
                    var message = response.data.message;
                    swal({
                            title: "成功",
                            text: message,
                            type: "success",
                            showCancelButton: false,
                            confirmButtonClass: "btn-success",
                            confirmButtonText: "确定",
                            closeOnConfirm: true
                        },
                        function () {
                            $('#loading').modal('hide');
                            if (mngId == 0) {
                                mngList.push(response.data);
                            } else {
                                var len = mngList.length;
                                for (var i = 0; i < len; i++) {
                                    if (mngList[i].managerCenterId == response.data.managerCenterId) {
                                        mngList[i] = response.data;
                                        break;
                                    }
                                }
                            }

                            $("#zone_mng_id").val("");
                            $("*[name = 'manager']").each(function(){
                                if($(this).val() == 0){
                                    $(this).attr("checked","checked");
                                }
                            })
                            buildTree();
                        });
                }
                else {
                    swal("失败", response.message, "error");
                }
            },
        });
    });

    //新增分区
    $('#btnSaveZone').click(function () {

        var staffNum = $('#manager1').val() == 0?"":commonClass.replaceAllQuotationMarks($('#manager1').val());
        var managerName = staffNum == 0?"":$("#manager1 option:selected").text();

        var zoneId = $('#zoneId').val();
        var zoneName = commonClass.replaceAllQuotationMarks($('#zoneName').val());
        var zoneper = $('#hidZoneper').val();
        var paZoneId = $('#paZone_id').val();
        var mngId = $('#zone_mng_id').val();
        var zoneDesc = commonClass.replaceAllQuotationMarks($('#zoneDesc').val());
        if (zoneName == null || zoneName == "") {
            swal("区域名不能为空", "", "warning");
            return;
        }
        var zoneLevel = paZoneId == 0 ? 1 : 2;
        var requestType = "POST";
        var requestUrl = "";
        var data = {
            "managerStaffNum": staffNum,
            "managerStaffName": managerName,
            "zoneName": zoneName,
            "zoneper": zoneper,
            "paZoneId": paZoneId,
            "zoneDesc": zoneDesc,
            "zoneLevel": zoneLevel,
            "manageCenterId":mngId
        };
        if (zoneId == 0) {
            requestType = "POST";
            requestUrl = webroot + "/zone/addzone";
        } else {
            requestType = "PUT";
            requestUrl = webroot + "/zone/updatezone"
            data.zoneId = zoneId;
        }
        $.ajax({
            type: requestType,
            url: requestUrl,
            contentType: "application/json;charset=utf-8",
            data: JSON.stringify(data),
            success: function (response) {
                if (response.code == 200) {
                    $('#area').modal('hide');
                    var message = response.data.message;
                    swal({
                            title: "成功",
                            text: message,
                            type: "success",
                            showCancelButton: false,
                            confirmButtonClass: "btn-success",
                            confirmButtonText: "确定",
                            closeOnConfirm: true
                        },
                        function () {
                            if (zoneId == 0) {
                                zoneList.push(response.data);
                            } else {
                                for (var i = 0; i < zoneList.length; i++) {

                                    if (zoneList[i].zoneId == response.data.zoneId) {
                                        zoneList[i] = response.data;
                                    }
                                    if(response.data.childZoneList!=null && response.data.childZoneList.length>0){
                                        var childZoneList = response.data.childZoneList;
                                        for(var j = 0; j < childZoneList.length; j++){
                                            var child = childZoneList[j];
                                            if(zoneList[i].zoneId == child.zoneId){
                                                zoneList[i] = child;
                                            }
                                        }
                                    }
                                }
                            }
                            buildTree();
                        });
                }
                else {
                    swal({
                            title: "失败",
                            text: response.message,
                            type: "error",
                            showCancelButton: false,
                            confirmButtonClass: "btn-success",
                            confirmButtonText: "确定",
                            closeOnConfirm: true
                        },
                        function () {
                            window.location.reload(true);
                        });
                }
            },
            beforeSend: function (XMLHttpRequest) {
                $("#btnSaveZone").attr("disabled", "disabled");
                $('#loading').modal('show');
            },
            complete: function (XMLHttpRequest, textStatus) {
                $("#btnSaveZone").removeAttr("disabled");
                $('#loading').modal('hide');
            }
        });
    });

    $(".mng_info").change(function () {
        var mngId = $(this).val();
        var currZoneList = $.grep(zoneList, function (value) {
            return value.manageCenterId == mngId && value.paZoneId == 0;
        });
        randerZoneSelect(currZoneList);
    });
});

function buildTree() {
    $.jstree.destroy();
    $('#jstree_demo_div').jstree({
        'core': {
            'data': buildMngTreeData()
        }
    });
    hideRightArea();
}

var mngMenu = [
    [{
        text: "编辑现场管理中心",
        func: function () {
            hideRightArea();

            var domId = $(this).attr("id");
            var arr = domId.split("_");
            var mngId = arr[1];
            var currlist = $.grep(mngList, function (value) {
                return value.managerCenterId == mngId;
            });
            var entiy = currlist[0];
            $('#managerCenterId').val(entiy.managerCenterId);
            loadManagerList(entiy.staffNum,0);
            $('#manageCenterName').val(entiy.managerCenterName);
            var status = entiy.manageCenterState;
            $('#hidBuState').val(status);
            $("#mngId").val("1");

            $('#areaBuStatus').children('label').removeClass("active");
            $($('#areaBuStatus').children('label')[status]).addClass("active");
            $("input[name=manageCenterState][value='"+status+"']").prop("checked", "checked");
            $('#mng_title').text("编辑现场管理中心");
            $('#mngStatusArea').show();
            $('#mng_block').show();
        }
    }, {
        text: "新增区域",
        func: function () {
            hideRightArea();
            $('#zoneper').children('label').removeClass("active");
            randerMngSelect();
            var domId = $(this).attr("id");
            var arr = domId.split("_");
            var mngId = arr[1];
            $('#zone_mng_id').val(mngId);
            var currZonelist = $.grep(zoneList, function (value) {
                return value.manageCenterId == mngId && value.paZoneId == 0;
            });
            $('#zone_title').text("新增区域");
            randerZoneSelect(currZonelist);
            loadManagerLists();
            $('#zoneName').val("");
            $('#mng_id').val(mngId);
            $('#zoneDesc').val("");
            $('#zone_block').show()
            $('#depStatusArea').hide();
        }
    }]
];

var paZoneMenu = [
    [{
        text: "编辑区域",
        func: function () {
            hideRightArea();
            $('#zone_block').show();
            var domId = $(this).attr("id");
            var arr = domId.split("_");
            var zoneId = arr[1];
            var zone = $.grep(zoneList, function (value) {
                return value.zoneId == zoneId;
            });
            var entiy = zone[0];
            var mngId = entiy.manageCenterId;
            randerMngSelect();
            $('#zone_mng_id').val(mngId);
            $('#zoneper').children('label').removeClass("active");
            $($('#zoneper').children('label')[entiy.zoneper - 1]).addClass("active");

            var currZonelist = $.grep(zoneList, function (value) {
                return value.manageCenterId == mngId && value.paZoneId == 0;
            });
            randerZoneSelect(currZonelist);

            $("#zone_title").text("编辑区域");
            $("#zoneName").val(entiy.zoneName);
            $("#zoneDesc").val(entiy.zoneDesc);
            $("#zoneId").val(entiy.zoneId);
            loadManagerList(entiy.managerStaffNum,1);
        }
    }, {
        text: "新增分区",
        func: function () {
            hideRightArea();
            $('#zone_block').show();
            $('#zoneper').children('label').removeClass("active");
            var domId = $(this).attr("id");
            var arr = domId.split("_");
            var paZoneId = arr[1];
            var zone = $.grep(zoneList, function (value) {
                return value.zoneId == paZoneId;
            });
            var entiy = zone[0];
            var mngId = entiy.manageCenterId;
            randerMngSelect();
            $('#zone_mng_id').val(mngId);
            var currZonelist = $.grep(zoneList, function (value) {
                return value.manageCenterId == mngId && value.paZoneId == 0;
            });
            randerZoneSelect(currZonelist);
            loadManagerLists();
            $('#zoneName').val("");

            $('#zone_title').text("新增分区");
            $('#zoneId').val(0);
            $('#paZone_id').val(paZoneId);
            $('#zoneType').children('label').removeClass("active");
            $($('#zoneType').children('label')[0]).addClass("active");
            $('#zoneName').val("");
            $('#zoneDesc').val("");
        }
    }]
];

var zoneMenu = [
    [{
        text: "编辑分区",
        func: function () {
            hideRightArea();
            $('#zone_block').show();

            var domId = $(this).attr("id");
            var arr = domId.split("_");
            var zoneId = arr[1];
            var zone = $.grep(zoneList, function (value) {
                return value.zoneId == zoneId;
            });
            var entiy = zone[0];

            var mngId = entiy.manageCenterId;
            randerMngSelect();
            $('#zone_mng_id').val(mngId);

            $('#zoneper').children('label').removeClass("active");
            $($('#zoneper').children('label')[entiy.zoneper - 1]).addClass("active");

            var currZonelist = $.grep(zoneList, function (value) {
                return value.manageCenterId == mngId && value.paZoneId == 0;
            });
            randerZoneSelect(currZonelist);

            $('#zone_title').text("编辑分区");
            $('#zoneId').val(entiy.zoneId);
            $('#hidZoneper').val(entiy.zoneper);
            $('#zoneper').children('label').removeClass("active");
            $($('#zoneper').children('label')[entiy.zoneper - 1]).addClass("active");
            $('#paZone_id').val(entiy.paZoneId);
            $('#zoneName').val(entiy.zoneName);
            $('#zoneDesc').val(entiy.zoneDesc);
            $('#zone_block').show();
            loadManagerList(entiy.managerStaffNum,1);
        }
    }, {
        text: "删除分区",
        func: function () {
            hideRightArea();
            var domId = $(this).attr("id");
            var arr = domId.split("_");
            var zoneId = arr[1];
            if (arr[0] == "pazone") {
                swal("警告", "区域不能直接删除", "error");
                return;
            }
            swal({
                    title: "确定删除该分区吗?",
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
                        url: webroot + "/zone/deletezone/" + zoneId,
                        success: function (response) {
                            if (response.code == 200) {
                                swal({
                                        title: "删除成功，同步到云学堂成功",
                                        text: "",
                                        type: "success",
                                        showCancelButton: false,
                                        confirmButtonClass: "btn-success",
                                        confirmButtonText: "确定",
                                        closeOnConfirm: true
                                    },
                                    function () {
                                        var len = zoneList.length;
                                        for (var i = 0; i < len; i++) {
                                            if (zoneList[i].zoneId == zoneId) {
                                                zoneList[i].status = 0;
                                                break;
                                            }
                                        }
                                        buildTree();
                                    });
                            }
                            else {
                                swal({
                                        title: "失败",
                                        text: response.message,
                                        type: "error",
                                        showCancelButton: false,
                                        confirmButtonClass: "btn-success",
                                        confirmButtonText: "确定",
                                        closeOnConfirm: true
                                    },
                                    function () {
                                        window.location.reload(true);
                                    });
                            }
                        }
                    });
                });
        }
    }]
];

function hideRightArea() {
    $('#mng_block').hide();
    $('#zone_block').hide();
}

function buildMngTreeData() {
    var treeNodes = [];
    for (var i = 0; i < mngList.length; i++) {
        var mng = mngList[i];
        var treeNode = {};
        treeNode.id = "mng_" + mng.managerCenterId;
        treeNode.text = mng.managerCenterName;
        treeNode.icon = "fa fa-group icon-state-success";
        treeNode.state = {opened: true};
        treeNode.children = buildPaZoneTreeData(mng.managerCenterId);
        treeNodes.push(treeNode);
    }
    return treeNodes;
}


function buildPaZoneTreeData(mngId) {
    console.log(JSON.stringify(zoneList))
    var currlist = $.grep(zoneList, function (value) {
        return value.manageCenterId === mngId && value.status === 1;
    });

    var treeNodes = [];
    for (var i = 0; i < currlist.length; i++) {
        var zone = currlist[i];
        if (zone.zoneLevel === 1) {
            var treeNode = {};
            treeNode.id = "pazone_" + zone.zoneId;
            treeNode.text = zone.zoneName;
            treeNode.icon = "fa fa-folder icon-state-warning";
            treeNode.state = {opened: true};
            treeNode.children = buildZoneTreeData(currlist, zone.zoneId);
            treeNodes.push(treeNode);
        }
    }
    return treeNodes;
}

function buildZoneTreeData(palist, paZoneId) {
    var currlist = $.grep(palist, function (value) {
        return value.paZoneId === paZoneId && value.status === 1;
    });
    var treeNodes = [];
    for (var i = 0; i < currlist.length; i++) {
        var zone = currlist[i];
        var treeNode = {};
        treeNode.id = "zone_" + zone.zoneId;
        treeNode.text = zone.zoneName;
        treeNode.icon = "fa fa-file icon-state-warning";
        treeNode.state = {opened: true};
        treeNodes.push(treeNode);
    }
    return treeNodes;
}

function randerMngSelect() {
    $(".mng_info").find("option").remove();
    $("<option></option>").val("0").text("--选择现场管理中心--").appendTo($(".mng_info"));
    $.each(mngList, function (i, item) {
        $("<option></option>").val(item["managerCenterId"]).text(item["managerCenterName"]).appendTo($(".mng_info"));
    });
}

function randerZoneSelect(list) {
    $(".pazone_info").find("option").remove();
    $("<option></option>").val("0").text("--选择上级分区--").appendTo($(".pazone_info"));
    $.each(list, function (i, item) {
        $("<option></option>").val(item["zoneId"]).text(item["zoneName"]).appendTo($(".pazone_info"));
    });
}


function loadManagerLists(){
    $("#manager").html("");
    $("#manager1").html("");
    var element = "<option value=\"0\">---选择负责人---</option>";
    if(managerLists.length!=0){
        for(var i=0;i<managerLists.length;i++){
            var staff = managerLists[i];
            element+= "<option name='staff' value='"+staff.staffNum+"'>"+staff.staffNameLocal+"</option> ";
        }
    }
    $("#manager").append(element);
    $("#manager1").append(element);
}

function loadManagerList(staffNum,id) {
    $("#manager").html("");
    $("#manager1").html("")
    var element = "<option value=\"0\">---选择负责人---</option>";
    if(managerLists.length!=0){
        for(var i=0;i<managerLists.length;i++){
            var staff = managerLists[i];
            element+= "<option name='staff' value='"+staff.staffNum+"'>"+staff.staffNameLocal+"</option> ";
        }
    }
    if(id==0){
        $("#manager").append(element);
        $("#manager option[value='"+staffNum+"']").attr("selected","selected");
    }else{
        $("#manager1").append(element);
        $("#manager1 option[value='"+staffNum+"']").attr("selected","selected");
    }

}

