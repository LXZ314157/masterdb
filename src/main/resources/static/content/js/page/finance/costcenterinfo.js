var costcenterId = commonClass.getQueryString("costcenterId");
var lanCode = commonClass.getQueryString("lanCode");
$(function(){
    getcostcenerinfo(costcenterId,lanCode);
})

function getcostcenerinfo(costcenterId,lanCode){
    $.ajax({
        type: "POST",
        url: webroot + "/finance/costcenterinfo",
        data: {
            'costcenterId': costcenterId,
            'lanCode': lanCode
        },
        success: function (response) {
            var costcenterinfo = response.data;
            $("#costcenterId").val(costcenterinfo.costcenterId);
            $("#lanCode").val(costcenterinfo.lanCode);
            $("#sourceId").val(costcenterinfo.sourceId);
            $("#costCenterName").val(costcenterinfo.costCenterName);
            $("#departmentId").val(costcenterinfo.departmentId);
            $("#costcenterManager").val(costcenterinfo.costcenterManager);
            $("#costcenterChief").val(costcenterinfo.costcenterChief);
            $("#costcenterVp").val(costcenterinfo.costcenterVp);
            $("#costcenterDesc").val(costcenterinfo.costcenterDesc);
            $("#costcenterState").val(costcenterinfo.costcenterState);
            $("#effectiveDate").val(costcenterinfo.effectiveDate);
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