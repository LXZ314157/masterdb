var cityLevelList = [];
var areaList = [];
var table;
var TableDatatablesButtons = function () {
    var initDataTable = function () {
        table = $('#resplist');
        var oTable = table.dataTable({
            buttons: [
                {extend: '', className: 'btn blue btn-outline excelExport', text: '导出excel'}
            ],
            "fnServerParams": function (aoData) {
                AddParams(aoData);
            },
            "dom": "<'row' <'col-md-12'B>><'row'<'col-md-6 col-sm-12'l>r><'table-scrollable't><'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>",
            "bSort": false,
            "processing": false,
            "ajaxSource": webroot + "/finance/resplist",
            'aoColumns': [
                {"mData": "respcenterId", "sClass": "detail"},
                {"mData": "lanName", "sClass": "detail"},
                {"mData": "respcenterName", "sClass": "detail"},
                {"mData": "respcenterManager", "sClass": "detail"},
                {"mData": "respcenterDesc", "sClass": "detail"},
                {"mData": "effectiveDate", "sClass": "detail"},
                {
                    "mData": "buyerId", "sClass": "detail", "bSortable": false, "mRender": function (data, type, full) {
                        var entiy = commonClass.replaceAllQuotationMarks(JSON.stringify(full));
                        var respcenterId = full.respcenterId;
                        var lanCode = full.lanCode;
                        var html = "<a href='" + webroot + "/finance/respcenterinfo?respcenterId=" + respcenterId + "&lanCode="+ lanCode + "'  class='btn btn-xs green btn-outline' rel='tooltip' title='查看详情' data-entiy='" + entiy + "' ><i class='fa fa-file-text-o'></i></a>";
                        return html;
                    }
                }
            ],
            "fnDrawCallback": function () {
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

function AddParams(aoData) {
    aoData.push({"name": "respcenterId", "value": commonClass.getStringVal($('#respcenterId').val())});
    aoData.push({"name": "lanCode", "value": commonClass.getStringVal($('#lanCode').val())});
    $('#loading').modal('show');
}

$(function () {
    loadLanguage();
    TableDatatablesButtons.init();

    document.onkeydown = function (e) {
        if (!e) e = window.event;
        if ((e.keyCode || e.which) == 13) {
            event.preventDefault();
            table.fnDraw();
        }
    }

    $(".excelExport").click(function () {
        $("#respcenterIdOrName").val($('#respcenterId').val());
        $("#lanCodes").val($('#lanCode').val());

        $("#frmMoedlDownload").attr("action", webroot + "/finance/downLoadRespcenterExcel");
        $("#frmMoedlDownload").attr("method", "POST");
        $("#frmMoedlDownload").submit();
    });
});



function loadLanguage() {
    $.ajax({
        type: "GET",
        url: webroot + "/hr/getlanguage",
        data: {
        },
        success: function (response) {
            if(response.code==200){
                var ele = "";
                var languagelist = response.data;
                for(var i = 0 ;i < languagelist.length; i++){
                    var language = languagelist[i];
                    ele+= "<option value='"+language.lanCode+"'>"+language.lanName+"</option>";
                }
                $("#lanCode").append(ele);
            }else{
                swal(ICICLELangUtil.getText("", 1163), ICICLELangUtil.getText("", 1064), "error");
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
}



