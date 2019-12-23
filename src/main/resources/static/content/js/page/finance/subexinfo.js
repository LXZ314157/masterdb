var subExcategoryId = commonClass.getQueryString("subExcategoryId");
var lanCode = commonClass.getQueryString("lanCode");
$(function(){
    getsubexinfo(subExcategoryId,lanCode);
})

function getsubexinfo(subExcategoryId,lanCode){
    $.ajax({
        type: "POST",
        url: webroot + "/finance/subexinfo",
        data: {
            'subExcategoryId': subExcategoryId,
            'lanCode': lanCode
        },
        success: function (response) {
            var subexinfo = response.data;
            $("#subExcategoryId").val(subexinfo.subExcategoryId);
            $("#lanCode").val(subexinfo.lanCode);
            $("#sourceId").val(subexinfo.sourceId);
            $("#subExcategoryName").val(subexinfo.subExcategoryName);
            $("#costcenterId").val(subexinfo.costcenterId);
            $("#subExcategoryDesc").val(subexinfo.subExcategoryDesc);
            $("#effectiveDate").val(subexinfo.effectiveDate);
            $("#subExcategoryState").val(subexinfo.subExcategoryState);
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