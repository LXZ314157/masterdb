$(document).ready(function () {

    $('.edittype').click(function () {
        $('#typename').val($(this).data('name'));
        $('#typedesc').val($(this).data('desc'));
        $(".statedefault").addClass("active");
        $('#savetype').data('tid', $(this).data('levelid'));
        $('#addType').modal('show');
        $('.statedefault ').addClass("active");
    });

    $('#newtype').click(function () {
        $('#typenameadd').val('');
        $('#typedescadd').val('');
        $('#savetype').data('tid', '0');
        $('#insertType').modal('show');
    });
    //新增代理商
    $('#inserttype').click(function () {
        var buyerTypeName = commonClass.replaceAllQuotationMarks($('#typenameadd').val());
        var buyerTypeDesc = commonClass.replaceAllQuotationMarks($('#typedescadd').val());
        var isDeleted = $('#stateadd input[name="rstate"]:checked').val();
        var data = [];
        console.log(isDeleted);
        if (buyerTypeName.length <= 0) {
            //请填写代理商类别
            swal(ICICLELangUtil.getText("",1486), "", "error");
        }
        else {
            data = {'buyerTypeName': buyerTypeName, 'buyerTypeDesc': buyerTypeDesc};
            $.ajax({
                type: "POST",
                data: data,
                url: webroot + "/buyer/typeinsert",
                success: function (data) {
                    console.log(data.code);
                    if (data.code == 200) {
                        swal({
                            //新增信息成功，请前往查看,确定
                                title: ICICLELangUtil.getText("",1048),
                                text: "",
                                type: "success",
                                showCancelButton: false,
                                confirmButtonClass: "btn-success",
                                confirmButtonText: ICICLELangUtil.getText("",1007),
                                closeOnConfirm: true
                            },
                            function () {
                                location.reload();
                            });
                    }
                    else {
                        swal(data.message, "", "error");
                        $('#typenameadd').val("");
                    }

                },
                beforeSend: function (XMLHttpRequest) {
                    $("#inserttype").attr("disabled", "disabled");
                    $('#loading').modal('show');
                },
                complete: function (XMLHttpRequest, textStatus) {
                    $("#inserttype").removeAttr("disabled");
                    $('#loading').modal('hide');
                },
                error: function () {
                    //程序出现未知错误，请稍候重试
                    swal(ICICLELangUtil.getText("",1035), "", "error");
                }

            });
        }
    });
    //编辑更新代理商
    $('#savetype').click(function () {
        var buyerTypeName = commonClass.replaceAllQuotationMarks($('#typename').val());
        var buyerTypeDesc = commonClass.replaceAllQuotationMarks($('#typedesc').val());
        var status = $('#state .active input').val();
        var buyerTypeId = $(this).data('tid');
        console.log(buyerTypeId);
        if (buyerTypeName.length <= 0) {
            //请填写代理商级别
            swal(ICICLELangUtil.getText("",1486), "", "error");
        }
        else if (status == undefined) {
            //请选择代理商状态
            swal(ICICLELangUtil.getText("",1487), "", "error");
        }
        else {
            data = {
                "buyerTypeId": buyerTypeId,
                "buyerTypeName": buyerTypeName,
                "buyerTypeDesc": buyerTypeDesc,
                'status': status
            };
            $.ajax({
                type: "post",
                data: data,
                url: webroot + "/buyer/typeupdate",
                success: function (response) {
                    if (response.code == 200) {
                        swal({
                            //更新成功,确定
                                title:ICICLELangUtil.getText("",1167) ,
                                text: "",
                                type: "success",
                                showCancelButton: false,
                                confirmButtonClass: "btn-success",
                                confirmButtonText: ICICLELangUtil.getText("",1007),
                                closeOnConfirm: true
                            },
                            function () {
                                location.reload();
                            });
                    } else {
                        swal(response.message, "", "error");
                    }
                },
                beforeSend: function (XMLHttpRequest) {
                    $("#savetype").attr("disabled", "disabled");
                    $('#loading').modal('show');
                },
                complete: function (XMLHttpRequest, textStatus) {
                    $("#savetype").removeAttr("disabled");
                    $('#loading').modal('hide');
                },
                error: function () {
                    //程序出现未知错误，请稍候重试
                    swal(ICICLELangUtil.getText("",1035), "", "error");
                }
            });
        }
    });
});