/**
 * Created by liumingming on 2017-10-25.
 */
var listOfLvl1 = [];
var listOfLvl2 = [];
var listOfLvl3 = [];
var listOfLvl4 = [];
var listOfLvl5 = [];
var listOfLvl6 = [];
var listOfLvl7 = [];
var departmentMatrixList = [];
var deptList = [];
var deptMatrix;
var table;

$(function () {
    $('#jstree_dept_div').jstree();

    $.ajax({
        type: "GET",
        url: webroot + "/hr/deptlist",
        success: function (response) {
            if (response.code == 200) {
                listOfLvl1 = response.data.deptListOfLvl1;
                listOfLvl2 = response.data.deptListOfLvl2;
                listOfLvl3 = response.data.deptListOfLvl3;
                listOfLvl4 = response.data.deptListOfLvl4;
                listOfLvl5 = response.data.deptListOfLvl5;
                listOfLvl6 = response.data.deptListOfLvl6;
                listOfLvl7 = response.data.deptListOfLvl7;
                departmentMatrixList = response.data.departmentMatrixList;
                buildTree();
            }
        },
        error: function () {
            swal(ICICLELangUtil.getText("", 1163), ICICLELangUtil.getText("", 1064), "error");
        },
        beforeSend: function (XMLHttpRequest) {
            $('#loading').modal('show');
        },
        complete: function (XMLHttpRequest, textStatus) {
            $('#loading').modal('hide');
        }
    });

    $("#jstree_dept_div").on("mousedown", "a", function () {
        var attrId = $(this).attr("id");
        var attrList = attrId.split("_");
        var type = attrList[0];
        var id = attrList[1];
        var data = [];
        if (type === "dept1") {
            deptList = listOfLvl1;
        } else if (type == "dept2"){
            deptList = listOfLvl2;
        }else if(type == "dept3"){
            deptList = listOfLvl3;
        }else if(type == "dept4"){
            deptList = listOfLvl4;
        }else if(type == "dept5"){
            deptList = listOfLvl5;
        }else if(type == "dept6"){
            deptList = listOfLvl6;
        }else if(type == "dept7"){
            deptList = listOfLvl7;
        }

        data = deptData;
        var currlist = $.grep(deptList, function (value) {
            return value.departmentId == id;
        });
        var deptMatrixList = $.grep(departmentMatrixList, function (value) {
            return value.departmentId == id;
        });
        var entiy = currlist[0];
        deptMatrix = deptMatrixList[0];
        $.smartMenu.remove();
        $(this).smartMenu(data);

    });

});

var deptData = [
    [{
        text: "部门角色",
        func: function () {
            $('#dept_block').show();
            $('#staff_block').hide();

            $("#department_id").val(deptMatrix.departmentId);
            $('#principal_level1').val(deptMatrix.principalLevel1);
            $('#principal_level1').attr("title",deptMatrix.principalLevel1Code);

            $('#principal_level2').val(deptMatrix.principalLevel2);
            $('#principal_level2').attr("title",deptMatrix.principalLevel2Code);

            $('#chief').val(deptMatrix.chief);
            $('#chief').attr("title",deptMatrix.chiefCode);

            $('#president').val(deptMatrix.president);
            $('#president').attr("title",deptMatrix.presidentCode);

            $('#vice_president').val(deptMatrix.vicePresident);
            $('#vice_president').attr("title",deptMatrix.vicePresidentCode);

            $('#hr_bp').val(deptMatrix.hrBp);
            $('#hr_bp').attr("title",deptMatrix.hrBpCode);

            $('#member01').val(deptMatrix.member01);
            $('#member01').attr("title",deptMatrix.member01Code);

            $('#member02').val(deptMatrix.member02);
            $('#member02').attr("title",deptMatrix.member02Code);

            $('#member03').val(deptMatrix.member03);
            $('#member03').attr("title",deptMatrix.member03Code);

            $('#member04').val(deptMatrix.member04);
            $('#member04').attr("title",deptMatrix.member04Code);

            $('#member05').val(deptMatrix.member05);
            $('#member05').attr("title",deptMatrix.member05Code);

            $('#member06').val(deptMatrix.member06);
            $('#member06').attr("title",deptMatrix.member06Code);
        }
    }, {
        text: "部门人员",
        func: function () {
            $('#staff_block').show();
            $('#dept_block').hide();

            $('#deptStafflist').dataTable({
                "fnServerParams": function (aoData) {
                    AddParams(aoData);
                },
                "dom": "<'row' <'col-md-12'B>><'row'<'col-md-6 col-sm-12'l>r><'table-scrollable't><'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>",
                "bSort": false,
                "processing": false,
                "ajaxSource": webroot + "/hr/deptStaffList",
                'aoColumns': [
                    {"mData": "staffNum", "sClass": "detail"},
                    {"mData": "staffNameLocal", "sClass": "detail"},
                    {"mData": "gender", "sClass": "detail"},
                    {"mData": "mobile", "sClass": "detail"},
                    {"mData": "superiorName", "sClass": "detail"},
                    {"mData": "postName", "sClass": "detail"}
                ],
                "fnDrawCallback": function () {
                    $('#loading').modal('hide');
                }
            });

        }
    }]
];

function buildTree() {
    $.jstree.destroy();
    $('#jstree_dept_div').jstree({
        'core': {
            'data': buildTreeData()
        }
    });
    $('#dept_block').hide();
}



function buildTreeData() {
    var treeNodes = [];
    for (var i = 0; i < listOfLvl1.length; i++) {
        var dept = listOfLvl1[i];
        var treeNode = {};
        treeNode.id = "dept1_" + dept.departmentId;
        treeNode.text = dept.departmentName;
        treeNode.icon = "fa fa-binoculars icon-state-danger";
        treeNode.state = {opened: true};
        var curr = $.grep(listOfLvl2, function (value) {
            return value.paDepartmentId === dept.departmentId;
        });
        treeNode.children = buildLvl2TreeData(curr);
        treeNodes.push(treeNode);
    }
    return treeNodes;
}

function buildLvl2TreeData(currDepList) {
    var treeNodes = [];
    for (var i = 0; i < currDepList.length; i++) {
        var dept = currDepList[i];
        var treeNode = {};
        treeNode.id = "dept2_" + dept.departmentId;
        treeNode.text = dept.departmentName;
        treeNode.icon = "fa fa-group icon-state-success";
        treeNode.state = {opened: true};
        treeNode.children = buildLvl3TreeData(dept.departmentId);
        treeNodes.push(treeNode);
    }
    return treeNodes;
}


function buildLvl3TreeData(deptId) {
    var currlist = $.grep(listOfLvl3, function (value) {
        return value.paDepartmentId === deptId ;
    });
    var treeNodes = [];
    for (var i = 0; i < currlist.length; i++) {
        var dept = currlist[i];
        var treeNode = {};
        treeNode.id = "dept3_" + dept.departmentId;
        treeNode.text = dept.departmentName;
        treeNode.icon = "fa fa-file icon-state-warning";
        treeNode.state = {opened: false};
        treeNode.children = buildLvl4TreeData(dept.departmentId);
        treeNodes.push(treeNode);
    }
    return treeNodes;
}

function buildLvl4TreeData(deptId) {
    var currlist = $.grep(listOfLvl4, function (value) {
        return value.paDepartmentId === deptId ;
    });
    var treeNodes = [];
    for (var i = 0; i < currlist.length; i++) {
        var dept = currlist[i];
        var treeNode = {};
        treeNode.id = "dept4_" + dept.departmentId;
        treeNode.text = dept.departmentName;
        treeNode.icon = "fa fa-file icon-state-warning";
        treeNode.state = {opened: false};
        treeNode.children = buildLvl5TreeData(dept.departmentId);
        treeNodes.push(treeNode);
    }
    return treeNodes;
}

function buildLvl5TreeData(deptId) {
    var currlist = $.grep(listOfLvl5, function (value) {
        return value.paDepartmentId === deptId ;
    });
    var treeNodes = [];
    for (var i = 0; i < currlist.length; i++) {
        var dept = currlist[i];
        var treeNode = {};
        treeNode.id = "dept5_" + dept.departmentId;
        treeNode.text = dept.departmentName;
        treeNode.icon = "fa fa-file icon-state-warning";
        treeNode.state = {opened: false};
        treeNode.children = buildLvl6TreeData(dept.departmentId);
        treeNodes.push(treeNode);
    }
    return treeNodes;
}

function buildLvl6TreeData(deptId) {
    var currlist = $.grep(listOfLvl6, function (value) {
        return value.paDepartmentId === deptId ;
    });
    var treeNodes = [];
    for (var i = 0; i < currlist.length; i++) {
        var dept = currlist[i];
        var treeNode = {};
        treeNode.id = "dept6_" + dept.departmentId;
        treeNode.text = dept.departmentName;
        treeNode.icon = "fa fa-file icon-state-warning";
        treeNode.state = {opened: false};
        treeNode.children = buildLvl7TreeData(dept.departmentId);
        treeNodes.push(treeNode);
    }
    return treeNodes;
}

function buildLvl7TreeData(deptId) {
    var currlist = $.grep(listOfLvl7, function (value) {
        return value.paDepartmentId === deptId ;
    });
    var treeNodes = [];
    for (var i = 0; i < currlist.length; i++) {
        var dept = currlist[i];
        var treeNode = {};
        treeNode.id = "dept7_" + dept.departmentId;
        treeNode.text = dept.departmentName;
        treeNode.icon = "fa fa-file icon-state-warning";
        treeNode.state = {opened: false};
        treeNodes.push(treeNode);
    }
    return treeNodes;
}

function AddParams(aoData) {
    var departmentId = deptMatrix.departmentId;
    aoData.push({"name": "departmentId", "value": departmentId});
    $('#loading').modal('show');
}

function sync2OA(){
    var data = {
        "departmentId":$("#department_id").val(),
        "principalLevel1": $('#principal_level1').attr("title"),
        "principalLevel2": $('#principal_level2').attr("title"),
        "chief": $('#chief').attr("title"),
        "president": $('#president').attr("title"),
        "vicePresident": $('#vice_president').attr("title"),
        "hrBp": $('#hr_bp').attr("title"),
        "member01": $('#member01').attr("title"),
        "member02": $('#member02').attr("title"),
        "member03": $('#member03').attr("title"),
        "member04": $('#member04').attr("title"),
        "member05": $('#member05').attr("title"),
        "member06": $('#member06').attr("title")
    };
    $.ajax({
        type: "POST",
        dataType: "json",
        url: webroot + "/hr/syncdepartmentmatrix",
        data: JSON.stringify(data),
        contentType: "application/json;charset=utf-8",
        success: function (response) {
            var message = response.message==null?"":response.message;
            if (response.code == 200) {
                swal({
                        title: "同步成功",
                        text: message,
                        type: "success",
                        showCancelButton: false,
                        confirmButtonClass: "btn-success",
                        confirmButtonText: "确定",
                        closeOnConfirm: true
                    })
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
        },
        error: function () {
            //程序出现未知错误，请稍候重试
            swal(ICICLELangUtil.getText("", 1035), "", "error");
        }
    });





}