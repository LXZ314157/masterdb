var staffNum = commonClass.getQueryString("staffNum");
$(function(){
    getstaffdetailinfo(staffNum);
})

function getstaffdetailinfo(staffNum){
    $.ajax({
        type: "GET",
        url: webroot + "/hr/viewstaffdetailinfo",
        data: {
            'staffNum': staffNum
        },
        success: function (response) {
            var staff = response.data;
            $("#staffNum").val(staff.staffNum);
            $("#staffNameLocal").val(staff.staffNameLocal);
            $("#costCenterId").val(staff.costCenterId);
            $("#credentialType").val(staff.credentialType);
            $("#birthday").val(staff.birthday);
            var mobile = staff.mobile;
            var mobileLength = mobile.length;
            if(mobileLength>4){
                var mobileSumString = getSum(mobileLength);
                mobile = mobile.replace(mobile.substring(0,mobileLength-4), mobileSumString);
            }
            $("#mobile").val(mobile);

            var credentialNum = staff.credentialNum;
            var credentialNumLength = credentialNum.length;
            if(credentialNumLength>4){
                var credentialNumSumString = getSum(credentialNumLength);
                credentialNum = credentialNum.replace(credentialNum.substring(0,credentialNumLength-4), credentialNumSumString);
            }
            $("#credentialNum").val(credentialNum);

            $("#email").val(staff.email);
            $("#phone").val(staff.phone);
            $("#extnum").val(staff.extnum);
            $("#doorcontrolnum").val(staff.doorcontrolnum);
            $("#staffState").val(staff.staffState);

            $("#joinDate").val(staff.joinDate);
            $("#demissionDate").val(staff.demissionDate);
            $("#isDirector").val(staff.isDirector);
            $("#postName").val(staff.postName);
            $("#staffLevel").val(staff.staffLevel);
            $("#company").val(staff.company);
            $("#superiorNum").val(staff.superiorNum);
            $("#officeLocation").val(staff.officeLocation);
            $("#staffType").val(staff.staffType);
            $("#departmentId").val(staff.departmentId);
            $("#jobSequence").val(staff.jobSequence);
            $("#league").val(staff.league);
            $("#nationality").val(staff.nationality);
            $("#ethnic").val(staff.ethnic);
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