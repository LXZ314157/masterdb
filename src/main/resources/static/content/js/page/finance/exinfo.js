var excategoryId = commonClass.getQueryString("excategoryId");
var lanCode = commonClass.getQueryString("lanCode");
$(function(){
    getexinfo(excategoryId,lanCode);
})

function getexinfo(excategoryId,lanCode){
    $.ajax({
        type: "POST",
        url: webroot + "/finance/exinfo",
        data: {
            'excategoryId': excategoryId,
            'lanCode': lanCode
        },
        success: function (response) {
            var exinfo = response.data;
            $("#excategoryId").val(exinfo.excategoryId);
            $("#lanCode").val(exinfo.lanCode);
            $("#sourceId").val(exinfo.sourceId);
            $("#excategoryName").val(exinfo.excategoryName);
            $("#costcenterId").val(exinfo.costcenterId);
            $("#excategoryDesc").val(exinfo.excategoryDesc);
            $("#effectiveDate").val(exinfo.effectiveDate);
            $("#excategoryState").val(exinfo.excategoryState);
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