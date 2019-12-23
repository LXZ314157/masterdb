var cityLevelList = [];
var areaList = [];
var table;
var TableDatatablesButtons = function () {
    var initDataTable = function () {
        table = $('#buyerlist');
        var oTable = table.dataTable({

            buttons: [
                {extend: '', className: 'btn green btn-outline addbuyer marginset', text: "新增代理商" },
                {extend: '', className: 'btn blue btn-outline excelExport', text: '导出excel'}
            ],
            "fnServerParams": function (aoData) {
                AddParams(aoData);
            },
            "dom": "<'row' <'col-md-12'B>><'row'<'col-md-6 col-sm-12'l>r><'table-scrollable't><'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>",
            "bSort": false,
            "processing": false,
            "ajaxSource": webroot + "/buyer/buyerlist",
            'aoColumns': [
                {"mData": "buyerId", "sClass": "detail"},
                {"mData": "buyerName", "sClass": "detail"},
                {"mData": "buyerContact", "sClass": "detail"},
                {"mData": "buyerPhone", "sClass": "detail"},
                {"mData": "attrdeposit", "sClass": "detail"},
                {"mData": "dealerplatformflag", "sClass": "detail"},
                {"mData": "depositratio", "sClass": "detail"},
                {"mData": "buyerState", "sClass": "detail"}, {
                    "mData": "buyerId", "sClass": "detail", "bSortable": false, "mRender": function (data, type, full) {
                        var entiy = commonClass.replaceAllQuotationMarks(JSON.stringify(full));
                        var no = full.buyerNo;
                        var html = "<a href='" + webroot + "/buyer/buyerinfo?id=" + no + "' class='btn btn-xs green btn-outline' rel='tooltip' title='编辑' data-entiy='" + entiy + "' ><i class='fa fa-pencil'></i></a>";
                        return html;
                    }
                }
            ],
            "aoColumnDefs": [
                {
                    'aTargets': [7],
                    'mRender': function (data, type, row) {
                        return row.buyerState == 1 ? "正常" : "结束合作"
                    }
                },
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
    aoData.push({"name": "buyerName", "value": commonClass.getStringVal($('#buyername').val())});
    aoData.push({"name": "buyerState", "value": commonClass.getStringVal($('#way').val())});
    $('#loading').modal('show');
}

$(function () {
    TableDatatablesButtons.init();
    $(".addbuyer").click(function () {
        window.location.href= webroot +"/buyer/buyerinfo";
    })
    document.onkeydown = function (e) {
        if (!e) e = window.event;
        if ((e.keyCode || e.which) == 13) {
            event.preventDefault();
            table.fnDraw();
        }
    }

    $(".excelExport").click(function () {
        $("#buyerNameOrId").val($('#buyername').val());
        $("#buyerState").val($('#way').val());

        $("#frmMoedlDownload").attr("action", webroot + "/buyer/downLoadBuyerExcel");
        $("#frmMoedlDownload").attr("method", "POST");
        $("#frmMoedlDownload").submit();
    });

});

