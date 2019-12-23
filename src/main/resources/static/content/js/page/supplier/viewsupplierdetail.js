var supplierCode = commonClass.getQueryString("supplierCode");
$(function(){
    getsupplierdetailinfo(supplierCode);
})

function getsupplierdetailinfo(supplierCode){
    $.ajax({
        type: "GET",
        url: webroot + "/supplier/viewsupplierdetailinfo",
        data: {
            'supplierCode': supplierCode
        },
        success: function (response) {
            var supplier = response.data;
            $("#supplierCode").val(supplier.supplierCode);
            $("#supplierName").val(supplier.supplierName);
            $("#supplierDescription").val(supplier.supplierDescription);
            $("#supplierCSuppliertypeId").val(supplier.supplierCSuppliertypeId);
            $("#supplierLegalperson").val(supplier.supplierLegalperson);
            $("#supplierContacter").val(supplier.supplierContacter);
            $("#supplierAddress").val(supplier.supplierAddress);
            $("#supplierPostal").val(supplier.supplierPostal);
            $("#supplierPhone").val(supplier.supplierPhone);
            $("#supplierMobile").val(supplier.supplierMobile);
            $("#supplierCCountryId").val(supplier.supplierCCountryId);
            $("#supplierCProvinceId").val(supplier.supplierCProvinceId);
            $("#supplierCCityId").val(supplier.supplierCCityId);
            $("#supplierEmail").val(supplier.supplierEmail);
            $("#supplierFax").val(supplier.supplierFax);
            $("#supplierDepositBank").val(supplier.supplierDepositBank);
            $("#supplierAccount").val(supplier.supplierAccount);
            $("#supplierTaxno").val(supplier.supplierTaxno);
            $("#supplierCompanyname").val(supplier.supplierCompanyname);
            $("#supplierDataStatusName").val(supplier.supplierDataStatusName);
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

function getSum(num){
    var result = "";
    for(var i = 0 ;i <num-4; i++){
        result+="*";
    }
    return result;
}