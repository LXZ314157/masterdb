var workgroup = [];
var clothestype = [];
var codestandard = [];
var naturesenson = [];
var wave = [];
var band = [];
var group = [];
var line = [];

var sizeGroup = [];
var year = [];
var uom = [];
var standard = [];
var devSeasons = [];
$(function () {
    //加载下拉框
    $.ajax({
        type: "GET",
        url: webroot + "/product/dropdown",
        success: function (response) {
            if (response.code == 200) {
                var resultlist = response.data;
                for (var i = 0; i < resultlist.length; i++) {
                    if (resultlist[i].tableName == "select_band") {
                        band.push({"itemKey": resultlist[i].itemKey, "itemValue": resultlist[i].itemValue})
                    }
                    if (resultlist[i].tableName == "select_wave") {
                        wave.push({"itemKey": resultlist[i].itemKey, "itemValue": resultlist[i].itemValue})
                    }
                    if (resultlist[i].tableName == "select_work_group") {
                        workgroup.push({"itemKey": resultlist[i].itemKey, "itemValue": resultlist[i].itemValue})
                    }
                    if (resultlist[i].tableName == "select_clothes_type") {
                        clothestype.push({"itemKey": resultlist[i].itemKey, "itemValue": resultlist[i].itemValue})
                    }
                    if (resultlist[i].tableName == "select_code_standard") {
                        codestandard.push({"itemKey": resultlist[i].itemKey, "itemValue": resultlist[i].itemValue})
                    }
                    if (resultlist[i].tableName == "select_nature_season") {
                        naturesenson.push({"itemKey": resultlist[i].itemKey, "itemValue": resultlist[i].itemValue})
                    }
                    if (resultlist[i].tableName == "select_group") {
                        group.push({"itemKey": resultlist[i].itemKey, "itemValue": resultlist[i].itemValue})
                    }
                    if (resultlist[i].tableName == "select_line") {
                        line.push({"itemKey": resultlist[i].itemKey, "itemValue": resultlist[i].itemValue})
                    }
                    if (resultlist[i].tableName == "select_size_group") {
                        sizeGroup.push({"itemKey": resultlist[i].itemKey, "itemValue": resultlist[i].itemValue})
                    }
                    if (resultlist[i].tableName == "select_uom") {
                        uom.push({"itemKey": resultlist[i].itemKey, "itemValue": resultlist[i].itemValue})
                    }
                    if (resultlist[i].tableName == "select_standard") {
                        standard.push({"itemKey": resultlist[i].itemKey, "itemValue": resultlist[i].itemValue})
                    }
                    if (resultlist[i].tableName == "select_dev_season") {
                        devSeasons.push({"itemKey": resultlist[i].itemKey, "itemValue": resultlist[i].itemValue})
                    }
                }
                loadSelectAll(workgroup, "workgroup");
                loadSelectAll(clothestype, "productclass");
                loadSelectAll(codestandard, "stylerule");
                loadSelectAll(naturesenson, "natureseason");
                loadSelectAll(wave, "wave");
                loadSelectAll(band, "band");
                loadSelectAll(group, "group");
                loadSelectAll(line, "line");
                loadSelectAll(sizeGroup, "sizegroup");
                loadSelectAll(standard, "standard");
                loadSelectAll(devSeasons, "devseason");
                loadSelectAll(uom, "uom");
                loadYear();
            }
            else {
                swal("下拉框数据获取失败，请稍后", "", Error);
            }
        },
        error: function () {
            //程序出现未知错误，请稍候重试
            swal(ICICLELangUtil.getText("", 1035), "", "error");
        }
    })
})

function loadSelectAll(selectlist, id) {
    var html = "<option value=''>默认</option>"
    for (var i = 0; i < selectlist.length; i++) {
        var content = selectlist[i];
        html += "<option value='" + content.itemKey + "'>" + content.itemValue + "</option>";

    }
    $("#" + id).append(html);
}

function loadYear() {
    var html = "<option value=''>默认</option>"
    for (var i = 1997; i < new Date().getFullYear() + 10; i++) {
        html += "<option value='" + i + "'>" + i + "</option>";
    }
    $("#year").append(html);
}
