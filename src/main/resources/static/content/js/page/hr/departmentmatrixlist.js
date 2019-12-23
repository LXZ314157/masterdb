var cityLevelList = [];
var areaList = [];
var table;
var TableDatatablesButtons = function () {
    var initDataTable = function () {
        table = $('#departmentmatrixlist');
        var oTable = table.dataTable({
            "fnServerParams": function (aoData) {
                AddParams(aoData);
            },
            "dom": "<'row' <'col-md-12'B>><'row'<'col-md-6 col-sm-12'l>r><'table-scrollable't><'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>",
            "bSort": false,
            "processing": false,
            "ajaxSource": webroot + "/hr/departmentMatrixList",
            'aoColumns': [
                {"mData": "departmentId", "sClass": "detail"},
                {"mData": "principalLevel1", "sClass": "detail"},
                {"mData": "principalLevel2", "sClass": "detail"},
                {"mData": "chief", "sClass": "detail"},
                {"mData": "vicePresident", "sClass": "detail"},
                {"mData": "president", "sClass": "detail"},
                {"mData": "hrBp", "sClass": "detail"},
                {"mData": "member01", "sClass": "detail"},
                {
                    "mData": "buyerId", "sClass": "detail", "bSortable": false, "mRender": function (data, type, full) {
                        var entiy = commonClass.replaceAllQuotationMarks(JSON.stringify(full));
                        var no = full.buyerNo;
                        var html = "<a href='" + webroot + "/buyer/buyerinfo?id=" + no + "' class='btn btn-xs green btn-outline' rel='tooltip' title='编辑' data-entiy='" + entiy + "' ><i class='fa fa-pencil'></i></a>";
                        return html;
                    }
                }
            ],
            "aoColumnDefs": [
                // {
                //     'aTargets': [6],
                //     'mRender': function (data, type, row) {
                //         var starttime = row.buyerStartDate;
                //         var len = new Date(starttime);
                //         var result = commonClass.date_time_long(len);
                //         return result;
                //
                //     }
                // },
                // {
                //     'aTargets': [1],
                //     'mRender': function (data, type, row) {
                //         return row.buyerState == 1 ? "正常" : "结束合作"
                //     }
                // },
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
    document.onkeydown = function (e) {
        if (!e) e = window.event;
        if ((e.keyCode || e.which) == 13) {
            event.preventDefault();
            table.fnDraw();
        }
    }

    // $("#buyername,#way").keydown(function (event) {
    //     if (event.keyCode == "13") {
    //         event.preventDefault();
    //         table.fnDraw();
    //     }
    //
    // })
});
