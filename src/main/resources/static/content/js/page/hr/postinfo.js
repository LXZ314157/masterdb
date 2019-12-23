var postId = commonClass.getQueryString("postId");
var lanCode = commonClass.getQueryString("lanCode");
$(function(){
    getpostinfo(postId);
})

function getpostinfo(postId){
    $.ajax({
        type: "POST",
        url: webroot + "/hr/postinfo",
        data: {
            'postId': postId,
            'lanCode': lanCode
        },
        success: function (response) {
            var post = response.data;
            $("#postId").val(post.postId);
            $("#lanCode").val(post.lanCode);
            $("#sourceId").val(post.sourceId);
            $("#postName").val(post.postName);
            $("#departmentId").val(post.departmentId);
            $("#paPostId").val(post.paPostId);
            $("#charger").val(post.charger);
            $("#postState").val(post.postState);

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