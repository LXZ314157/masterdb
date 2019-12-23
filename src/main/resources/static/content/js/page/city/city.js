var cityLevelList = [];
var areaList = [];
var table;

var TableDatatablesButtons = function () {
    var initDataTable = function () {
        table = $('#datatable');
        var oTable = table.dataTable({
            buttons: [
                {extend: '', className: 'btn red btn-outline btnshow', text: '显示高级查询条件'}
            ],
            "fnServerParams": function (aoData) {
                AddParams(aoData);
            },
            "bSort": false,
            "dom": "<'row' <'col-md-12'B>><'row'<'col-md-6 col-sm-12'l>r><'table-scrollable't><'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>",
            "ajaxSource": webroot + "/city/citylist",
            'aoColumns': [
                {"mData": "cityId", "sClass": "detail"}, //城市ID
                {"mData": "cityName", "sClass": "detail"}, //城市名称
                {"mData": "cityCode", "sClass": "detail"}, //城市编码
                {"mData": "provinceName", "sClass": "detail"}, //省份
                {"mData": "cityLevelName", "sClass": "detail"}, //城市级别
                {"mData": "areaName", "sClass": "detail"}, //所属地区
                {
                    "mData": "cityId", "sClass": "detail", "bSortable": false, "mRender": function (data, type, full) {
                    var entiy = commonClass.replaceAllQuotationMarks(JSON.stringify(full));
                    var html = "<a href='javascript:;' class='btn btn-xs green btn-outline' rel='tooltip' title='编辑' data-entiy='" + entiy + "' onclick='editCity(this);'><i class='fa fa-pencil'></i></a>";

                    return html;
                }
                } //操作
            ],
            "fnDrawCallback": function () {
                $('#loading').modal('hide');
                $('#city_search').modal('hide');
                $("#btnSearchCity").removeAttr("disabled");
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

$(function () {
    TableDatatablesButtons.init();

    $.ajax({
        type: "GET",
        url: webroot + "/city/citylevellist",
        success: function (response) {
            if (response.code == 200) {
                $("#tbCityLevel").html("");
                var html = "";
                cityLevelList = response.data;
                var len = cityLevelList.length;
                if (len > 0) {
                    html = "";
                    var cityLevel;
                    for (var i = 0; i < len; i++) {
                        cityLevel = cityLevelList[i];
                        html += randerCityLevelLine(cityLevel);
                    }
                }
                randerCityLevelSelect();
                $("#tbCityLevel").html(html);
            }
            else {
                cityLevelList = [];
                $("#tbCityLevel").html("");
                swal(response.message, "", "error");
            }
        }
    });

    $.ajax({
        type: "GET",
        url: webroot + "/city/provincelist",
        success: function (response) {
            if (response.code == 200) {
                randerProvinceSelect(response.data);
            }
        }
    });

    //地市
    $.ajax({
        type: "GET",
        url: webroot + "/city/areacitylist",
        success: function (response) {
            if (response.code == 200) {
                randerAreaCitySelect(response.data);
            }
        }
    });
    //区县
    $.ajax({
        type: "GET",
        url: webroot + "/city/countylist",
        success: function (response) {
            if (response.code == 200) {
                randerCountySelect(response.data);
            }
        }
    });


    $.ajax({
        type: "GET",
        url: webroot + "/city/arealist",
        success: function (response) {
            if (response.code == 200) {
                $("#tbArea").html("");
                var html = "";
                areaList = response.data;
                var len = areaList.length;
                if (len > 0) {
                    html = "";
                    var area;
                    for (var i = 0; i < len; i++) {
                        area = areaList[i];
                        html += randerAreaLine(area);
                    }
                }
                randerAreaSelect();
                $("#tbArea").html(html);
            }
            else {
                cityLevelList = [];
                $("#tbArea").html("");
                swal(response.message, "", "error");
            }
        }
    });

    $('.btnAddCityLevel').click(function () {
        editOrAddCityLevel(null);
    });

    $('.btnAddArea').click(function () {
        editOrAddArea(null);
    });

    $('#btnSaveCityLevel').click(function () {
        var cityLevelId = $('#cityLevelId').val();
        var cityLevelName = commonClass.replaceAllQuotationMarks($('#cityLevelName').val());
        var cityLevelDesc = commonClass.replaceAllQuotationMarks($('#cityLevelDesc').val());
        if (cityLevelName == null || cityLevelName == "") {
            swal("城市级别名不能为空", "", "error");
            return;
        }
        var requestType = "";
        var requestUrl = "";
        var data = {};
        if (cityLevelId == 0) {
            requestType = "POST";
            requestUrl = webroot + "/city/addcitylevel";
            data = {"cityLevelName": cityLevelName, "cityLevelDesc": cityLevelDesc};
        }
        else {
            requestType = "PUT";
            requestUrl = webroot + "/city/updatecitylevel";
            var status = $('#hidCityLevelState').val()
            data = {
                "cityLevelId": cityLevelId,
                "cityLevelName": cityLevelName,
                "cityLevelDesc": cityLevelDesc,
                "status": status
            };
        }

        $.ajax({
            type: requestType,
            url: requestUrl,
            contentType: "application/json;charset=utf-8",
            data: JSON.stringify(data),
            success: function (response) {
                if (response.code == 200) {
                    $('#citylevel').modal('hide');
                    swal({
                            title: "成功",
                            text: "",
                            type: "success",
                            showCancelButton: false,
                            confirmButtonClass: "btn-success",
                            confirmButtonText: "确定",
                            closeOnConfirm: true
                        },
                        function () {
                            if (cityLevelId == 0) {
                                cityLevelList.push(response.data);
                                var html = randerCityLevelLine(response.data);
                                $("#tbCityLevel").append($(html));
                                randerCityLevelSelect();
                            } else {
                                var len = cityLevelList.length;
                                for (var i = 0; i < len; i++) {
                                    if (cityLevelList[i].cityLevelId == response.data.cityLevelId) {
                                        cityLevelList[i] = response.data;
                                        break;
                                    }
                                }
                                reranderCityLevelLine(response.data);
                            }
                        });
                }
                else {
                    swal("失败", response.message, "error");
                }
            },
            beforeSend: function (XMLHttpRequest) {
                $("#btnSaveCityLevel").attr("disabled", "disabled");
                $('#loading').modal('show');
            },
            complete: function (XMLHttpRequest, textStatus) {
                $("#btnSaveCityLevel").removeAttr("disabled");
                $('#loading').modal('hide');
            }
        });
    });

    $('#btnSaveArea').click(function () {
        var areaId = $('#areaId').val();
        var areaCode = commonClass.replaceAllQuotationMarks($('#areaCode').val());
        var areaName = commonClass.replaceAllQuotationMarks($('#areaName').val());
        if (areaCode == null || areaCode == "") {
            swal("区域编码不能为空", "", "error");
            return;
        }
        if (areaName == null || areaName == "") {
            swal("区域名不能为空", "", "error");
            return;
        }
        var requestType = "";
        var requestUrl = "";
        var data = {};
        if (areaId == 0) {
            requestType = "POST";
            requestUrl = webroot + "/city/addarea";
            data = {"areaCode": areaCode, "areaName": areaName};
        }
        else {
            requestType = "PUT";
            requestUrl = webroot + "/city/updatearea";
            var status = $('#hidAreaState').val()
            data = {
                "areaId": areaId,
                "areaCode": areaCode,
                "areaName": areaName,
                "status": status
            };
        }

        $.ajax({
            type: requestType,
            url: requestUrl,
            contentType: "application/json;charset=utf-8",
            data: JSON.stringify(data),
            success: function (response) {
                if (response.code == 200) {
                    $('#area').modal('hide');
                    swal({
                            title: "成功",
                            text: "",
                            type: "success",
                            showCancelButton: false,
                            confirmButtonClass: "btn-success",
                            confirmButtonText: "确定",
                            closeOnConfirm: true
                        },
                        function () {
                            if (areaId == 0) {
                                areaList.push(response.data);
                                var html = randerAreaLine(response.data);
                                randerAreaSelect();
                                $("#tbArea").append($(html));
                            } else {
                                var len = areaList.length;
                                for (var i = 0; i < len; i++) {
                                    if (areaList[i].areaId == response.data.areaId) {
                                        areaList[i] = response.data;
                                        break;
                                    }
                                }
                                reranderAreaLine(response.data);
                            }
                        });
                }
                else {
                    swal("失败", response.message, "error");
                }
            },
            beforeSend: function (XMLHttpRequest) {
                $("#btnSaveArea").attr("disabled", "disabled");
                $('#loading').modal('show');
            },
            complete: function (XMLHttpRequest, textStatus) {
                $("#btnSaveArea").removeAttr("disabled");
                $('#loading').modal('hide');
            }
        });
    });

    $('.btn-toggle-status').children("label").each(function (i) {
        $(this).click(function () {
            $($(this).parent().children('input')[0]).val($(this).data("val"));
        });
    });

    $('.btnshow').click(function () {
        $('#city_search').modal('show');
    });

    $('#btnSearchCity').click(function () {
        $(this).attr('disabled', "true");
        table.fnDraw();
    });

    $('#btnEditCity').click(function () {
        var cityId = $('#city_id').val();
        var cityName = commonClass.replaceAllQuotationMarks($('#city_name').val());
        var cityLevelId = commonClass.replaceAllQuotationMarks($('#level_id').val());
        var cityCode = commonClass.replaceAllQuotationMarks($('#city_code').val());
        var areaId = commonClass.replaceAllQuotationMarks($('#area_id').val());
        var provinceId = commonClass.replaceAllQuotationMarks($('#province_id').val());

        if (cityName == null || cityName == "") {
            swal("城市名称不能为空", "", "error");
            return;
        }
        if (cityLevelId == null || cityLevelId == "0" || cityLevelId == 0) {
            swal("城市级别不能为空", "", "error");
            return;
        }
        if (provinceId == null || provinceId == "0" || provinceId == 0) {
            swal("所属省份不能为空", "", "error");
            return;
        }
        var data = {
            "cityId": cityId,
            "cityName": cityName,
            "cityLevelId": cityLevelId,
            "cityCode": cityCode,
            "areaId": areaId,
            "provinceId": provinceId
        };

        $.ajax({
            type: "PUT",
            url: webroot + "/city/updatecity",
            contentType: "application/json;charset=utf-8",
            data: JSON.stringify(data),
            success: function (response) {
                if (response.code == 200) {
                    $('#city_edit').modal('hide');
                    swal({
                            title: "成功",
                            text: "",
                            type: "success",
                            showCancelButton: false,
                            confirmButtonClass: "btn-success",
                            confirmButtonText: "确定",
                            closeOnConfirm: true
                        },
                        function () {
                            table.fnDraw();
                        });
                }
                else {
                    swal("失败", response.message, "error");
                }
            },
            beforeSend: function (XMLHttpRequest) {
                $("#btnEditCity").attr("disabled", "disabled");
                $('#loading').modal('show');
            },
            complete: function (XMLHttpRequest, textStatus) {
                $("#btnEditCity").removeAttr("disabled");
                $('#loading').modal('hide');
            }
        });
    });
});

function deleteCityLevel(curr) {
    var cityLevelId = $(curr).data('citylevelid');
    swal({
            title: "确定删除该城市级别吗?",
            text: "",
            type: "warning",
            showCancelButton: true,
            cancelButtonText: "取消",
            confirmButtonClass: "btn-danger",
            confirmButtonText: "确定",
            closeOnConfirm: false
        },
        function () {
            $.ajax({
                type: "DELETE",
                url: webroot + "/city/deletecitylevel/" + cityLevelId,
                success: function (response) {
                    if (response.code == 200) {
                        swal({
                                title: "删除成功",
                                text: "",
                                type: "success",
                                showCancelButton: false,
                                confirmButtonClass: "btn-success",
                                confirmButtonText: "确定",
                                closeOnConfirm: true
                            },
                            function () {
                                var len = cityLevelList.length;
                                for (var i = 0; i < len; i++) {
                                    if (cityLevelList[i].cityLevelId == cityLevelId) {
                                        cityLevelList[i].status = 0;
                                        reranderCityLevelLine(cityLevelList[i])
                                        break;
                                    }
                                }
                            });
                    }
                    else {
                        swal("删除失败!", response.message, "error");
                    }
                }
            });
        });
}

function deleteArea(curr) {
    var areaId = $(curr).data('areaid');
    swal({
            title: "确定删除该地理区域吗?",
            text: "",
            type: "warning",
            showCancelButton: true,
            cancelButtonText: "取消",
            confirmButtonClass: "btn-danger",
            confirmButtonText: "确定",
            closeOnConfirm: false
        },
        function () {
            $.ajax({
                type: "DELETE",
                url: webroot + "/city/deletearea/" + areaId,
                success: function (response) {
                    if (response.code == 200) {
                        swal({
                                title: "删除成功",
                                text: "",
                                type: "success",
                                showCancelButton: false,
                                confirmButtonClass: "btn-success",
                                confirmButtonText: "确定",
                                closeOnConfirm: true
                            },
                            function () {
                                var len = areaList.length;
                                for (var i = 0; i < len; i++) {
                                    if (areaList[i].areaId == areaId) {
                                        areaList[i].status = 0;
                                        reranderAreaLine(areaList[i])
                                        break;
                                    }
                                }
                            });
                    }
                    else {
                        swal("删除失败!", response.message, "error");
                    }
                }
            });
        });
}

function editOrAddCityLevel(curr) {
    if (curr == null) {
        $('#citylevel_title').html('新增城市级别');
        $('#cityLevelId').val("0");
        $('#cityLevelName').val("");
        $('#cityLevelDesc').val("");
        $('#citylevelStatusArea').hide();
    }
    else {
        $('#citylevel_title').html('编辑城市级别');
        var entiy = $(curr).data('entiy');
        $('#cityLevelId').val(entiy.cityLevelId);
        $('#cityLevelName').val(entiy.cityLevelName);
        $('#cityLevelDesc').val(entiy.cityLevelDesc);
        var status = entiy.status;
        $('#hidCityLevelState').val(status);
        $('#cityLevelStatus').children('label').removeClass("active");
        $($('#cityLevelStatus').children('label')[status]).addClass("active");
        $('#citylevelStatusArea').show();
    }
    $('#citylevel').modal('show');
}

function editOrAddArea(curr) {
    if (curr == null) {
        $('#area_title').html('新增地理区域');
        $('#areaId').val("0");
        $('#areaCode').val("");
        $('#areaName').val("");
        $('#areaStatusArea').hide();
    }
    else {
        $('#area_title').html('编辑地理区域');
        var entiy = $(curr).data('entiy');
        $('#areaId').val(entiy.areaId);
        $('#areaCode').val(entiy.areaCode);
        $('#areaName').val(entiy.areaName);
        var status = entiy.status;
        $('#hidAreaState').val(status);
        $('#areaStatus').children('label').removeClass("active");
        $($('#areaStatus').children('label')[status]).addClass("active");
        $('#areaStatusArea').show();
    }
    $('#area').modal('show');
}

function randerCityLevelLine(cityLevel) {
    var html = "";
    html += '<tr id="citylevel_' + cityLevel.cityLevelId + '">';
    html += randerCityLevelTRLine(cityLevel);
    html += "</tr>";
    return html;
}

function randerCityLevelTRLine(cityLevel) {
    var html = "";
    var entiy = commonClass.replaceAllQuotationMarks(JSON.stringify(cityLevel));
    html += "<td>" + cityLevel.cityLevelName + "</td>";
    html += "<td>" + cityLevel.cityLevelDesc + "</td>";
    if (cityLevel.status == 1) {
        html += "<td>有效</td>";
    }
    else {
        html += "<td>无效</td>";
    }
    html += "<td>";
    if (cityLevel.status == 1) {
        html += "<a href='javascript:;' class='btn btn-xs red btn-outline' rel='tooltip' title='删除' data-citylevelid='" + cityLevel.cityLevelId + "' onclick='deleteCityLevel(this);'><i class='fa fa-remove'></i></a>";
    }
    html += "<a href='javascript:;' class='btn btn-xs green btn-outline' rel='tooltip' title='编辑' data-entiy='" + entiy + "' onclick='editOrAddCityLevel(this);'><i class='fa fa-pencil'></i></a>";
    html += "</td>";
    return html;
}

function reranderCityLevelLine(cityLevel) {
    var html = randerCityLevelTRLine(cityLevel);
    $('#citylevel_' + cityLevel.cityLevelId).html(html);
}


function randerAreaLine(areaData) {
    var html = "";
    html += '<tr id="area_' + areaData.areaId + '">';
    html += randerAreaTRLine(areaData);
    html += "</tr>";
    return html;
}

function randerAreaTRLine(areaData) {
    var html = "";
    var entiy = commonClass.replaceAllQuotationMarks(JSON.stringify(areaData));
    html += "<td>" + areaData.areaCode + "</td>";
    html += "<td>" + areaData.areaName + "</td>";
    if (areaData.status == 1) {
        html += "<td>有效</td>";
    }
    else {
        html += "<td>无效</td>";
    }
    html += "<td>";
    if (areaData.status == 1) {
        html += "<a href='javascript:;' class='btn btn-xs red btn-outline' rel='tooltip' title='删除' data-areaid='" + areaData.areaId + "' onclick='deleteArea(this);'><i class='fa fa-remove'></i></a>";
    }
    html += "<a href='javascript:;' class='btn btn-xs green btn-outline' rel='tooltip' title='编辑' data-entiy='" + entiy + "' onclick='editOrAddArea(this);'><i class='fa fa-pencil'></i></a>";
    html += "</td>";
    return html;
}

function reranderAreaLine(areaData) {
    var html = randerAreaTRLine(areaData);
    $('#area_' + areaData.areaId).html(html);
}

function editCity(curr) {
    var entiy = $(curr).data('entiy');
    $('#city_name').val(entiy.cityName);
    $('#pa_city_name').val(entiy.paCityName);
    $('#city_id').val(entiy.cityId);
    $('#area_id').val(entiy.areaId);
    $('#province_id').val(entiy.provinceId);
    $('#city_code').val(entiy.cityCode);
    $('#level_id').val(entiy.cityLevelId);
    $('#province_id').attr("disabled","disabled");
    $('#city_edit').modal('show');
}

function AddParams(aoData) {
    aoData.push({"name": "cityLevelId", "value": commonClass.getStringVal($('#city_level_id').val())});
    aoData.push({"name": "cityName", "value": commonClass.getStringVal($('#cityName').val())});
    aoData.push({"name": "areaCityId", "value": commonClass.getStringVal($('#area_city').val())});
    aoData.push({"name": "countyId", "value": commonClass.getStringVal($('#county').val())});
    $('#loading').modal('show');
}

function randerCityLevelSelect() {
    $(".city_level").find("option").remove();
    $("<option></option>").val("0").text("--选择城市级别--").appendTo($(".city_level"));
    $.each(cityLevelList, function (i, item) {
        $("<option></option>").val(item["cityLevelId"]).text(item["cityLevelName"]).appendTo($(".city_level"));
    });
}

function randerAreaSelect() {
    $(".area_info").find("option").remove();
    $("<option></option>").val("0").text("--选择地区--").appendTo($(".area_info"));
    $.each(areaList, function (i, item) {
        $("<option></option>").val(item["areaId"]).text(item["areaName"]).appendTo($(".area_info"));
    });
}

function randerProvinceSelect(provinceList) {
    $(".province_info").find("option").remove();
    $("<option></option>").val("0").text("--选择省份--").appendTo($(".province_info"));
    $.each(provinceList, function (i, item) {
        $("<option></option>").val(item["provinceId"]).text(item["provinceName"]).appendTo($(".province_info"));
    });
}

function randerAreaCitySelect(areaCityList) {
    for (var i = 0; i < areaCityList.length; i++) {
        var areaCity = areaCityList[i];
        var html = "<option value='" + areaCity.cityId + "'>" + areaCity.cityName + "</option>";
        $("#area_city_id").append(html);
    }
}

function randerCountySelect(countyList) {

    for (var i = 0; i < countyList.length; i++) {
        var countCity = countyList[i];
        var html = "<option value='" + countCity.cityId + "'>" + countCity.cityName + "</option>";
        $("#county_id").append(html);
    }
}