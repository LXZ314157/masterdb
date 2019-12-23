var respcenterId = commonClass.getQueryString("respcenterId");
var lanCode = commonClass.getQueryString("lanCode");
$(function(){
    getrespcenterinfo(respcenterId,lanCode);
})

function getrespcenterinfo(respcenterId,lanCode){
    $.ajax({
        type: "POST",
        url: webroot + "/finance/respcenterinfo",
        data: {
            'respcenterId': respcenterId,
            'lanCode': lanCode
        },
        success: function (response) {
            var respcenterinfo = response.data;
            $("#respcenterId").val(respcenterinfo.respcenterId);
            $("#lanCode").val(respcenterinfo.lanCode);
            $("#sourceId").val(respcenterinfo.sourceId);
            $("#respcenterName").val(respcenterinfo.respcenterName);
            $("#respcenterManager").val(respcenterinfo.respcenterManager);
            // $("#departmentId").val(respcenterinfo.departmentId);
            $("#respcenterDesc").val(respcenterinfo.respcenterDesc);
            $("#respcenterState").val(respcenterinfo.respcenterState);
            $("#effectiveDate").val(respcenterinfo.effectiveDate);
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