var productCategoryCode = "";
function uploadExcelFile(){
    var selected = $("#productCategory").find("option:selected");
    var id = selected.val();
    if(selected.attr("name")=="costume" || selected.attr("id")=="product_costume"){
        ajaxHtml();
    }else{
        $("#upLoadFileForm").ajaxSubmit({
            type: "POST",
            url: webroot + "/product/uploadcategorylist",
            data:{
                "productCategoryCode":productCategoryCode
            },
            dataType: "json",
            success: function(response){
                if(response.code=='200'){
                    if(response.data.path==""){
                        swal({
                                title: response.data.message,
                                text: "Excel数据导入成功",
                                type: "success",
                                showCancelButton: false,
                                confirmButtonClass: "btn-success",
                                confirmButtonText: "确定",
                                closeOnConfirm: true
                            },
                            function () {
                                location.reload();
                            });
                    }else{
                        swal({
                                title: response.data.message,
                                text: "",
                                type: "warning",
                                showCancelButton: true,
                                cancelButtonText: "取消",
                                cancelButtonClass: "btn-cancel",
                                confirmButtonClass: "btn-danger excel",
                                confirmButtonText: "确定",
                                closeOnConfirm: false,
                            },
                            function () {
                                download(response.data.path);
                            })
                    }

                } else{
                    if(response.data.code=='201'){
                        var excelData = response.data.excelData;
                        if(excelData!=null){
                            swal({
                                    title: response.data.message,
                                    text: "",
                                    type: "warning",
                                    showCancelButton: true,
                                    cancelButtonText: "取消",
                                    cancelButtonClass: "btn-cancel",
                                    confirmButtonClass: "btn-danger excel",
                                    confirmButtonText: "确定",
                                    closeOnConfirm: false
                                },
                                function (isConfirm) {
                                    if(isConfirm){
                                        download(response.data.path);
                                    }else{
                                        if(excelData!=null){
                                            exportAllData(excelData);
                                        }
                                    }
                                })
                        }else{
                            swal({
                                    title: response.data.message,
                                    text: "",
                                    type: "error",
                                    showCancelButton: true,
                                    cancelButtonText: "取消",
                                    cancelButtonClass: "btn-cancel",
                                    confirmButtonClass: "btn-danger excel",
                                    confirmButtonText: "确定",
                                    closeOnConfirm: false
                                },
                                function (isConfirm) {
                                    if(isConfirm){
                                        download(response.data.path);
                                    }
                                })

                        }
                    }else{
                        return swal(response.data.message,"", "error");
                    }
                }


            },
            error: function () {
                swal(data.message, "", "error");
            },
            beforeSend: function (XMLHttpRequest) {
                $('#loading').modal('show');
            },
            complete: function (XMLHttpRequest, textStatus) {
                $('#loading').modal('hide');
            }
        })
    }
}

function productinfoSubmit(){
    if ($("#uploadfile").val() == "") {
        return swal("警告", "您还没有选择文件", "warning");
    }

    var formdata = new FormData(document.getElementById("upLoadFileForm1"));
    $.ajax({
        type: "POST",
        url: webroot + "/product/uploadproductinfo",
        data: formdata,
        processData: false,
        contentType: false,
        success: function (response) {
            if(response.code=='200'){
                if(response.data.path==null){
                    swal({
                            title: response.data.message,
                            text: "",
                            type: "success",
                            showCancelButton: false,
                            confirmButtonClass: "btn-success",
                            confirmButtonText: "确定",
                            closeOnConfirm: true
                        },
                        function () {
                            location.reload();
                        });
                }else{
                    swal({
                            title: response.data.message,
                            text: "",
                            type: "warning",
                            showCancelButton: true,
                            cancelButtonText: "取消",
                            cancelButtonClass: "btn-cancel",
                            confirmButtonClass: "btn-danger excel",
                            confirmButtonText: "确定",
                            closeOnConfirm: false
                        },
                        function () {
                            download(response.data.path);
                        })
                }

            } else{
                if(response.data.code=='201'){//全部数据错误
                    swal({
                            title: response.data.message,
                            text: "",
                            type: "error",
                            showCancelButton: true,
                            cancelButtonText: "取消",
                            cancelButtonClass: "btn-cancel",
                            confirmButtonClass: "btn-danger excel",
                            confirmButtonText: "确定",
                            closeOnConfirm: false
                        },
                        function () {
                            download(response.data.path);
                        })
                }else{//202 模板格式错误
                    return swal(response.data.message,"", "error");
                }
            }
        },
        beforeSend: function (XMLHttpRequest) {
            $('#loading').modal('show');
        },
        complete: function (XMLHttpRequest, textStatus) {
            $('#loading').modal('hide');
        },
        error: function () {
            //程序出现未知错误，请稍候重试
            swal(ICICLELangUtil.getText("", 1035), "", "error");
        }

    })

}



function ajaxHtml() {
    $("#upLoadFileForm").ajaxSubmit({
        type: "POST",
        url: webroot + "/product/uploadcostumelist",
        data:{
            "productCategoryCode":productCategoryCode
        },
        dataType: "json",
        success: function (response) {
            if (response.code == 200) {

                var data = response.data;
                var path = response.message;
                if (data == null || data.length == 0) {
                    swal({
                            title: "导入数据存在错误，需下载观看，请点击确定",
                            text: "",
                            type: "warning",
                            showCancelButton: true,
                            cancelButtonText: "取消",
                            cancelButtonClass: "btn-cancel",
                            confirmButtonClass: "btn-danger excel",
                            confirmButtonText: "确定",
                            closeOnConfirm: false
                        },
                        function () {
                            download(path);
                        })
                } else {
                    var count = response.data.count;
                    getAlertContent("成功导入" + count + "条", "已导入的数据不第二次导入");
                }
            }
            else {
                swal(response.message, "", "error");
            }
        },
        beforeSend: function (XMLHttpRequest) {
            $('#loading').modal('show');
            // $("#" + sub).attr("disabled", "disabled");
        },
        complete: function (XMLHttpRequest, textStatus) {
            // $("#" + sub).removeAttr("disabled");
            $('#loading').modal('hide');
        },
        error: function () {
            //程序出现未知错误，请稍候重试
            swal(ICICLELangUtil.getText("", 1035), "", "error");
        }

    })
}

$(function () {
    $("#styleinsert").click(function () {
        var id = $("#productCategory").val();
        if(id=="0"){
            return  swal("请先选择产品类别", "", "warning");
        }
        if (checkExcel()) {
            return;
        }
        uploadExcelFile();
    });
    loadProductCategory();
})

function checkExcel() {
    if (jQuery("input[type='file']").val() == "") {
        swal("警告", "您还没有选择文件", "warning");
        return true;
    }

}

function loadProductCategory(){
    $.ajax({
        type: "GET",
        url: webroot + "/product/listattributeitems",
        success: function (response) {
            if (response.code == 200) {
                productCategoryList = response.data;
                var html="";
                for (var i = 0; i < productCategoryList.length; i++) {
                    var productCategory = productCategoryList[i];
                    html+= "<option name='"+productCategory.tableName+"' id='"+productCategory.productCategoryCode+"' value='"+productCategory.productCategoryId+"'>"+productCategory.categoryName+"</option>";
                }
                $("#productCategory").append(html);


            }
            else {
                //失败
                swal(ICICLELangUtil.getText("", 1148), response.message, "error");
            }
        },
        error: function () {
            //您的网络似乎出了一些问题
            swal(ICICLELangUtil.getText("", 1105), "", "error");
        }
    });
}

function loadmodel(obj){
    var selected = $("#productCategory").find("option:selected");
    var id = selected.val();
    var tableName = $("#productCategory option:selected").attr("name");
    if(id=="0"){
      return  swal("请先选择产品类别", "", "warning");
    }
    downloadModel(id,tableName);
}


function download(path) {
    $("#excelpath").val(path);
    $("#frmDownload").attr("action", webroot + "/product/productModeldownload");
    $("#frmDownload").attr("method", "POST");
    $("#frmDownload").submit();
}

function downloadModel(id,tableName) {
    $("#productCategoryId").val(id);
    $("#tableName").val(tableName);
    $("#frmMoedlDownload").attr("action", webroot + "/product/exportexcel");
    $("#frmMoedlDownload").attr("method", "POST");
    $("#frmMoedlDownload").submit();
}

function downloadProductInfoModel() {
    $("#productInfoFrm").attr("action", webroot + "/product/downloadProductInfoModel");
    $("#productInfoFrm").attr("method", "POST");
    $("#productInfoFrm").submit();
}



function changeModel() {
    productCategoryCode = $("#productCategory option:selected").attr("id");
}

function getAlertContent(content) {
    swal({
            title: "成功",
            text: content,
            type: "success",
            showCancelButton: false,
            confirmButtonClass: "btn-success",
            confirmButtonText: "确定",
            closeOnConfirm: true
        },
        function () {
            location.reload();
        });
}

function getAlertContent(content) {
    swal({
            title: "成功",
            text: content,
            type: "success",
            showCancelButton: false,
            confirmButtonClass: "btn-success",
            confirmButtonText: "确定",
            closeOnConfirm: true
        },
        function () {
            location.reload();
        });
}

function exportAllData(excelDatas){
    $.ajax({
        url: webroot + "/product/importData",
        type: "POST",
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(excelDatas),
        success:function(response){
            if(response.code=='200'){
                if(response.data.path==""){
                    swal({
                            title: response.data.message,
                            text: "Excel数据导入成功",
                            type: "success",
                            showCancelButton: false,
                            confirmButtonClass: "btn-success",
                            confirmButtonText: "确定",
                            closeOnConfirm: true
                        },
                        function () {
                            location.reload();
                        });
                }else{
                    swal({
                            title: response.data.message,
                            text: "",
                            type: "warning",
                            showCancelButton: true,
                            cancelButtonText: "取消",
                            cancelButtonClass: "btn-cancel",
                            confirmButtonClass: "btn-danger excel",
                            confirmButtonText: "确定",
                            closeOnConfirm: false,
                        },
                        function () {
                            download(response.data.path);
                        })
                }
            } else{
                if(response.data.code=='201'){
                    swal({
                            title: response.data.message,
                            text: "",
                            type: "error",
                            showCancelButton: true,
                            cancelButtonText: "取消",
                            cancelButtonClass: "btn-cancel",
                            confirmButtonClass: "btn-danger excel",
                            confirmButtonText: "确定",
                            closeOnConfirm: false
                        },
                        function (isConfirm) {
                            if(isConfirm){
                                download(response.data.path);
                            }else{
                                var excelData = response.data.excelData;
                                if(excelData!=null){
                                    exportAllData(excelData);
                                }
                            }
                        })

                }else{
                    return swal(response.data.message,"", "error");
                }
            }
        },
            error:function(){
            return swal(response.data.message,"", "error");
        },
        beforeSend: function (XMLHttpRequest) {
            $('#loading').modal('show');
        },
        complete: function (XMLHttpRequest, textStatus) {
            $('#loading').modal('hide');
        }
    })

}

function submit(path) {
    $("#excelpath").val(path);
    $("#frmDownload").attr("action", webroot + "/product/download");
    $("#frmDownload").attr("method", "POST");
    $("#frmDownload").submit();
}