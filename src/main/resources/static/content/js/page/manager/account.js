var table;
var currentData = [];
var domainList = [];
var promissionGroupList = [];

var TableDatatablesButtons = function () {
    var initDataTable = function () {
        table = $('#datatable').dataTable({
            buttons: [
                {extend: '', className: 'btn green btn-outline btnAdd', text: '新增帐号'}
            ],
            "bSort": false,
            'language': {
                "search": "快速搜索(登录名或显示名)"
            },
            "ajaxSource": webroot + "/manager/getaccountlist",
            'aoColumns': [
                {
                    "mData": "id", "sClass": "detail", "bSortable": false, "mRender": function (data, type, full) {
                    var html = "<a href='javascript:;' class='btn btn-xs green btn-outline' data-id='" + data + "' onclick='editAccount(this);' rel='tooltip' title='编辑'><i class='fa fa-pencil'></i></a>";
                    return html;
                }
                }, //操作
                {"mData": "id", "sClass": "detail"}, //帐号id
                {"mData": "loginname", "sClass": "detail"}, //登录名
                {"mData": "truename", "sClass": "detail"}, //显示名
                {"mData": "domainName", "sClass": "detail"}, //域
                {"mData": "groupName", "sClass": "detail"}//权限组
            ],
            "fnDrawCallback": function (oSettings) {
                var json = JSON.parse(oSettings.jqXHR.responseText);
                currentData = json.aaData;
                $('#loading').modal('hide');
            }
        });
    }
    return {
        init: function () {
            if (!jQuery().dataTable) {
                return;
            }
            initDataTable();
        }
    };
}();

$(function () {
    $.ajax({
        type: "GET",
        url: webroot + "/manager/getselectdata",
        success: function (response) {
            if (response.code == 200) {
                domainList = response.data.domainList;
                promissionGroupList = response.data.promissionGroupList;
            }
            randerSelect();
        }
    });

    TableDatatablesButtons.init();

    $('.btnAdd').click(function () {
        loadDetail();
    });

    $("#btnSave").click(function () {
        var loginname = $.trim($('#loginname').val());
        var truename = $.trim($('#truename').val());
        var domainId = $.trim($('#domain').val());
        var groupId = $.trim($('#groupName').val());
        if (loginname == null || loginname.length === 0) {
            swal("登录名不能为空", "error");
            return;
        }
        if (truename == null || truename.length === 0) {
            swal("显示名不能为空", "error");
            return;
        }
        if (domainId == null || domainId.length === 0) {
            swal("请先选择域", "error");
            return;
        }
        if (groupId == null || groupId.length === 0) {
            swal("请先选择权限组", "error");
            return;
        }
        var formData = new FormData($("#form_detail")[0]);
        var requestUrl = webroot + "/manager/addaccount";
        var type = $('#type').val();
        if (type == 1) {
            requestUrl = webroot + "/manager/updateaccount";
        }
        $.ajax({
            type: "POST",
            url: requestUrl,
            data: formData,
            processData: false,
            contentType: false,
            success: function (response) {
                if (response.code == 200) {
                    swal({
                            title: "操作成功",
                            text: "",
                            type: "success",
                            showCancelButton: false,
                            confirmButtonClass: "btn-success",
                            confirmButtonText: "确定",
                            closeOnConfirm: true
                        },
                        function () {
                            $('#detail').modal("hide");
                            table.fnDraw();
                        });
                }
                else {
                    swal("操作失败", response.message, "error");
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                swal("操作失败", "[-999]未知错误:" + textStatus, "error");
            },
            beforeSend: function (XMLHttpRequest) {
                $('#loading').modal('show');
                $("#btnSave").attr("disabled", "disabled");
            },
            complete: function (XMLHttpRequest, textStatus) {
                $('#loading').modal('hide');
                $("#btnSave").removeAttr("disabled");
            }
        });

    });
});

function editAccount(curr) {
    var id = $(curr).data("id");
    var currlist = $.grep(currentData, function (value) {
        return value.id === id;
    });
    if (currlist != null && currlist.length > 0) {
        var account = currlist[0];
        loadDetail(account);
    }
}

function loadDetail(account) {
    var title = "新增帐号";
    var type = 0;
    var accountId = "";
    var loginname = "";
    var truename = "";
    var domainId = "";
    var groupId = "";
    if (account != null) {
        type = 1;
        title = "编辑帐号";
        accountId = account.id;
        loginname = account.loginname;
        truename = account.truename;
        domainId = account.domainId;
        groupId = account.groupId;
    }
    $('#type').val(type);
    $('#accountId').val(accountId);
    $('#detail_title').text(title);
    $('#loginname').val(loginname);
    $('#truename').val(truename);
    $('#domain').val(domainId);
    $('#groupName').val(groupId);
    $('#detail').modal("show");
}

function randerSelect() {
    randerDomainSelect();
    randerGroupSelect();
}

function randerDomainSelect() {
    $("#domain").find("option").remove();
    $("<option></option>").val("").text("--请选择--").appendTo($("#domain"));
    if (domainList != null && domainList.length > 0) {
        $.each(domainList, function (i, item) {
            $("<option></option>").val(item["id"]).text(item["domainName"]).appendTo($("#domain"));
        });
    }
}

function randerGroupSelect() {
    $("#groupName").find("option").remove();
    $("<option></option>").val("").text("--请选择--").appendTo($("#groupName"));
    if (promissionGroupList != null && promissionGroupList.length > 0) {
        $.each(promissionGroupList, function (i, item) {
            $("<option></option>").val(item["id"]).text(item["groupName"]).appendTo($("#groupName"));
        });
    }
}