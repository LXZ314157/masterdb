package com.icicle.masterdb.util;

import com.icicle.masterdb.model.StoreComparison;
import com.icicle.masterdb.model.ViewStore;
import com.icicle.masterdb.pojo.vo.*;
import com.icicle.masterdb.service.constant.CommonConstant;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.util.ResourceUtils.CLASSPATH_URL_PREFIX;
/**
 * @author liurenhua
 * @date 2017/11/7
 */
public class ExcelUtil {
    private static final String EXCEL_HEADER = "504B0304|D0CF11E0";
    private static final String IMAGE_HEADER = "89504E47|424DBE60|FFD8FFE0|47494638|FFD8FF|FFD8FFE1";
    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";
    /**
     * excel文件
     */
    public static final String EXCEL = "excel";

    /**
     * 判读文件是否为excel文件
     *
     * @param inputStream
     * @return
     */
    public static boolean isExcelFile(InputStream inputStream) {
        boolean isOk = false;
        String header = getFileHeader(inputStream);
        if (EXCEL_HEADER.contains(header)) {
            isOk = true;
        }
        return isOk;
    }

    /**
     * 判断一个文件是不是图片文件
     *
     * @param inputStream
     * @return
     */
    public static boolean isImageFile(InputStream inputStream) {
        boolean isOk = false;
        String header = getFileHeader(inputStream);
        if (header == null) {
            return false;
        }
        if (IMAGE_HEADER.contains(header)) {
            isOk = true;
        }
        return isOk;
    }

    /**
     * 按照给订的路径创建路径，如果存在则直接返回，不存在则递归创建目录
     *
     * @param path 路径
     */
    public static void createDir(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    /**
     * 根据文件路径获取文件头信息
     *
     * @param is 文件输入流
     * @return 文件头信息
     */
    public static String getFileHeader(InputStream is) {
        String value;
        try {
            byte[] b = new byte[4];
            is.read(b, 0, b.length);
            value = bytesToHexString(b);
        } catch (Exception e) {
            LogUtil.getLogger(ExcelUtil.class).error(e.getMessage(), e);
            return null;
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
        }
        return value;
    }

    /**
     * 将要读取文件头信息的文件的byte数组转换成string类型表示
     *
     * @param src 要读取文件头信息的文件的byte数组
     * @return 文件头信息
     */
    private static String bytesToHexString(byte[] src) {
        StringBuilder builder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        String hv;
        for (int i = 0; i < src.length; i++) {
            // 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式，并转换为大写
            hv = Integer.toHexString(src[i] & 0xFF).toUpperCase();
            if (hv.length() < 2) {
                builder.append(0);
            }
            builder.append(hv);
        }
        return builder.toString();
    }

    /**
     * @param fileType 上传的文件类型，例如图片的上传类型为images 则会建立一个image文件夹
     * @param url      文件上传的详细路径
     * @return
     */
    public static String getFilePath(String fileType, String url) {
        String path = "";
        try {
            String base = ResourceUtils.getFile(CLASSPATH_URL_PREFIX.concat("static")).getPath();
            path = StringUtil.gsFormat("{0}/upload/{1}/{2}/", base, fileType, url);
        } catch (FileNotFoundException e) {
            LogUtil.getLogger(ExcelUtil.class).error(e.getMessage(), e);
        }
        return path;
    }
    /**
     * 设置单元格颜色
     * @param style
     * @param row
     * @param j
     */
    public static void setCellColor(CellStyle style, Row row, Integer j){
        Cell  cellother=row.getCell(j);
        cellother.setCellStyle(style);

    }
    /**
     * 存储一个excel文件
     *
     * @param file
     */
    public static String saveExcelFile(MultipartFile file) throws IOException {
        String random = ExcelUtil.getRandomPath();
        String path = ExcelUtil.getFilePath(EXCEL, random);

        //如果路径不存在则创建这个路径  文件夹的绝对路径 不带文件名
        ExcelUtil.createDir(path);

        String excelUrl = path.concat(file.getOriginalFilename());

        //得到当前图片路径 带文件名
        File f = new File(excelUrl);
        file.transferTo(f);
        return excelUrl;
    }

    /**
     * 获取一个随机路径，命名规则为 八位数日期字符串/六位随机字符串
     *
     * @return
     */
    public static String getRandomPath() {
        String dateStr = DateUtil.getDateStringWithSeperate("", DateUtil.now());
        int randNum = (int) ((Math.random() * 9 + 1) * 100000);
        return String.format("%s/%s/", dateStr, randNum);
    }

    /**
     * 解析表格中某行的rowCount个单元格,返回成为数组
     * 返回的数组中的字符串中是不带空格的,带字符默认返回null
     *
     * @param row
     * @return
     */
    public static String[] parseRow(Row row, int rowCount) {
        if (row == null || rowCount <= 0) {
            return null;
        }

        String[] colValues = new String[rowCount];
        for (int i = 0; i < rowCount; i++) {
            Cell c = row.getCell(i);
            if (c != null) {
                c.setCellType(Cell.CELL_TYPE_STRING);
                colValues[i] = StringUtils.isEmpty(c.getStringCellValue().trim())?null:c.getStringCellValue().trim();
            }
        }

        return colValues;
    }


    /**
     * 判断Excel的版本,获取Workbook
     * @param file
     * @return
     * @throws IOException
     */
    public static Workbook getWorkbok(File file) throws IOException{
        Workbook wb = null;
        FileInputStream in = new FileInputStream(file);
        if(file.getName().endsWith(EXCEL_XLS)){     //Excel 2003
            wb = new HSSFWorkbook(in);
        }else if(file.getName().endsWith(EXCEL_XLSX)){    // Excel 2007/2010
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }

    public static Workbook getStoreWorkbook(List<ViewStore> list, List<Map<String,String>> mapList, List<StoreComparison> comparisonList){
        OutputStream out = null;
        Workbook workbook = new XSSFWorkbook();
        try {
            Sheet sheet = workbook.createSheet();
            Row firstRow = sheet.createRow(0);

            firstRow.createCell(0).setCellValue("店铺ID");
            firstRow.createCell(1).setCellValue("店铺编号");
            firstRow.createCell(2).setCellValue("店铺名");
            firstRow.createCell(3).setCellValue("代理商");
            firstRow.createCell(4).setCellValue("所属分区");
            firstRow.createCell(5).setCellValue("联系人");
            firstRow.createCell(6).setCellValue("联系电话");
            firstRow.createCell(7).setCellValue("店铺级别");
            firstRow.createCell(8).setCellValue("类型");
            firstRow.createCell(9).setCellValue("状态");
            firstRow.createCell(10).setCellValue("360对比店铺");
            firstRow.createCell(11).setCellValue("店铺负责人");
            firstRow.createCell(12).setCellValue("店铺简称");
            firstRow.createCell(13).setCellValue("拼音简写");
            firstRow.createCell(14).setCellValue("所在城市");
            firstRow.createCell(15).setCellValue("销售性质");
            firstRow.createCell(16).setCellValue("手机");
            firstRow.createCell(17).setCellValue("传真");
            firstRow.createCell(18).setCellValue("Email");
            firstRow.createCell(19).setCellValue("店铺面积");
            firstRow.createCell(20).setCellValue("开业时间");
            firstRow.createCell(21).setCellValue("结业时间");
            firstRow.createCell(22).setCellValue("合约期");
            firstRow.createCell(23).setCellValue("备注");
            firstRow.createCell(24).setCellValue("销售渠道");
            firstRow.createCell(25).setCellValue("店铺类型");
            firstRow.createCell(26).setCellValue("商场类型");
            firstRow.createCell(27).setCellValue("专柜地址");
            firstRow.createCell(28).setCellValue("所在楼层");
            firstRow.createCell(29).setCellValue("押金");
            firstRow.createCell(30).setCellValue("租金");
            firstRow.createCell(31).setCellValue("销售扣点");
            firstRow.createCell(32).setCellValue("最近重装日期");
            firstRow.createCell(33).setCellValue("装修标准");
            firstRow.createCell(34).setCellValue("装修年限");
            firstRow.createCell(35).setCellValue("店铺地址经度");
            firstRow.createCell(36).setCellValue("店铺地址维度");
            firstRow.createCell(37).setCellValue("店铺形象类别");
            firstRow.createCell(38).setCellValue("装修版本");
            firstRow.createCell(39).setCellValue("LOGO版本");
            firstRow.createCell(40).setCellValue("店铺类别");

            for(int i=0;i<list.size();i++){
                ViewStore viewStore = list.get(i);
                Row itemRow = sheet.createRow(i+1);

                String storeStateName = "";
                int storeState = viewStore.getStoreState();
                switch (storeState){
                    case 0 : storeStateName="新店待开";break;
                    case 1 : storeStateName="正常";break;
                    case 2 : storeStateName="闭店未关";break;
                    case 3 : storeStateName="关闭";break;
                }

                Cell storeIDCell = itemRow.createCell(0);
                Cell storeNoCell = itemRow.createCell(1);
                Cell storeNameCell = itemRow.createCell(2);
                Cell buyerCell = itemRow.createCell(3);
                Cell zoneCell = itemRow.createCell(4);
                Cell contactCell = itemRow.createCell(5);
                Cell phoneCell = itemRow.createCell(6);
                Cell storeLevelCell = itemRow.createCell(7);
                Cell typeCell = itemRow.createCell(8);
                Cell statusCell = itemRow.createCell(9);
                Cell storeCompareCell = itemRow.createCell(10);
                Cell storeChargerCell = itemRow.createCell(11);
                Cell storeShortNameCell = itemRow.createCell(12);
                Cell storePinSpellCell = itemRow.createCell(13);
                Cell cityCell = itemRow.createCell(14);
                Cell salePropertyCell = itemRow.createCell(15);
                Cell mobileCell = itemRow.createCell(16);
                Cell faxCell = itemRow.createCell(17);
                Cell emailCell = itemRow.createCell(18);
                Cell storeSizeCell = itemRow.createCell(19);
                Cell startTimeCell = itemRow.createCell(20);
                Cell closeTimeCell = itemRow.createCell(21);
                Cell cooperateTimeCell = itemRow.createCell(22);
                Cell remarksCell = itemRow.createCell(23);
                Cell ownerShipCell = itemRow.createCell(24);
                Cell storeClassCell = itemRow.createCell(25);
                Cell mallTypeCell = itemRow.createCell(26);
                Cell specialSaleCell = itemRow.createCell(27);
                Cell floorNumCell = itemRow.createCell(28);
                Cell depositCell = itemRow.createCell(29);
                Cell rentalCell = itemRow.createCell(30);
                Cell saleDeductionCell = itemRow.createCell(31);
                Cell recentRestoreCell = itemRow.createCell(32);
                Cell restoreStandardCell = itemRow.createCell(33);
                Cell restoreYearsCell = itemRow.createCell(34);
                Cell longitudeCell = itemRow.createCell(35);
                Cell latitudeCell = itemRow.createCell(36);
                Cell imageCategoryCell = itemRow.createCell(37);
                Cell restoreVersionCell = itemRow.createCell(38);
                Cell logoVersionCell = itemRow.createCell(39);
                Cell storeCategoryCell = itemRow.createCell(40);


                storeIDCell.setCellValue(viewStore.getStoreId());
                storeNoCell.setCellValue(viewStore.getStoreNo());
                storeNameCell.setCellValue(StringUtils.isEmpty(viewStore.getStoreName())?"":viewStore.getStoreName());
                buyerCell.setCellValue(StringUtils.isEmpty(viewStore.getBuyerName())?"":viewStore.getBuyerName());
                zoneCell.setCellValue(StringUtils.isEmpty(viewStore.getZoneName())?"":viewStore.getZoneName());
                contactCell.setCellValue(StringUtils.isEmpty(viewStore.getStoreContact())?"":viewStore.getStoreContact());
                phoneCell.setCellValue(StringUtils.isEmpty(viewStore.getStorePhone())?"":viewStore.getStorePhone());
                storeLevelCell.setCellValue(StringUtils.isEmpty(viewStore.getStoreLevelName())?"":viewStore.getStoreLevelName());
                typeCell.setCellValue(StringUtils.isEmpty(viewStore.getStoreTypeName())?"":viewStore.getStoreTypeName());
                statusCell.setCellValue(storeStateName);


                String compareStoreName = "";
                if(comparisonList!=null && !comparisonList.isEmpty()){
                    for(StoreComparison storeComparison : comparisonList){
                        if(viewStore.getStoreNo().equals(storeComparison.getStoreNo())){
                            compareStoreName+=storeComparison.getCompareStoreName()+",";
                        }
                    }
                }

                storeCompareCell.setCellValue(compareStoreName);
                storeChargerCell.setCellValue(StringUtils.isEmpty(viewStore.getStaffNameLocal())?"":viewStore.getStaffNameLocal());
                storeShortNameCell.setCellValue(StringUtils.isEmpty(viewStore.getStoreShortName())?"":viewStore.getStoreShortName());
                storePinSpellCell.setCellValue(StringUtils.isEmpty(viewStore.getStoreSpell())?"":viewStore.getStoreSpell());
                cityCell.setCellValue(StringUtils.isEmpty(viewStore.getCityName())?"":viewStore.getCityName());
                salePropertyCell.setCellValue(StringUtils.isEmpty(viewStore.getStoreTypeName())?"":viewStore.getStoreTypeName());
                mobileCell.setCellValue(StringUtils.isEmpty(viewStore.getStoreMobile())?"":viewStore.getStoreMobile());
                faxCell.setCellValue(StringUtils.isEmpty(viewStore.getStoreFax())?"":viewStore.getStoreFax());
                emailCell.setCellValue(StringUtils.isEmpty(viewStore.getStoreEmail())?"":viewStore.getStoreEmail());
                storeSizeCell.setCellValue(viewStore.getStoreSize()==null?"":viewStore.getStoreSize().toString());
                startTimeCell.setCellValue(viewStore.getOpenDate()==null?"":DateUtil.date2String(viewStore.getOpenDate()));
                closeTimeCell.setCellValue(viewStore.getCloseDate()==null?"":DateUtil.date2String(viewStore.getCloseDate()));
                cooperateTimeCell.setCellValue(viewStore.getContractPeriod()==null?"":viewStore.getContractPeriod().toString());
                remarksCell.setCellValue(StringUtils.isEmpty(viewStore.getStoreDesc())?"":viewStore.getStoreDesc());


                String storeOwnerShipId = StringUtils.isEmpty(viewStore.getStoreOwnership())?"":viewStore.getStoreOwnership().toString();
                String storeOwnerShipName = "";
                if(!StringUtils.isEmpty(storeOwnerShipId)){
                    List<Map<String,String>> filterList = mapList.stream().filter(a -> a.get("store_attrib_def_code").equals("store_ownership")).collect(Collectors.toList())
                            .stream().filter(a -> a.get("store_attrib_item_code").equals(storeOwnerShipId)).collect(Collectors.toList());
                    if(!filterList.isEmpty()){
                        storeOwnerShipName = filterList.get(0).get("store_attrib_item_name");
                    }
                }
                ownerShipCell.setCellValue(storeOwnerShipName);


                String storeClassId = StringUtils.isEmpty(viewStore.getStoreClass())?"":viewStore.getStoreClass().toString();
                String storeClassName = "";
                if(!StringUtils.isEmpty(storeClassId)){
                    List<Map<String,String>> filterList = mapList.stream().filter(a -> a.get("store_attrib_def_code").equals("store_class")).collect(Collectors.toList())
                            .stream().filter(a -> a.get("store_attrib_item_code").equals(storeClassId)).collect(Collectors.toList());
                    if(!filterList.isEmpty()){
                        storeClassName = filterList.get(0).get("store_attrib_item_name");
                    }
                }
                storeClassCell.setCellValue(storeClassName);

                String storeMallTypeId = StringUtils.isEmpty(viewStore.getMallType())?"":viewStore.getMallType().toString();
                String storeMallTypeName = "";
                if(!StringUtils.isEmpty(storeMallTypeId)){
                    List<Map<String,String>> filterList = mapList.stream().filter(a -> a.get("store_attrib_def_code").equals("mall_type")).collect(Collectors.toList())
                            .stream().filter(a -> a.get("store_attrib_item_code").equals(storeMallTypeId)).collect(Collectors.toList());
                    if(!filterList.isEmpty()){
                        storeMallTypeName = filterList.get(0).get("store_attrib_item_name");
                    }
                }
                mallTypeCell.setCellValue(storeMallTypeName);

                String storeImageCategoryId = StringUtils.isEmpty(viewStore.getStoreImageCategory())?"":viewStore.getStoreImageCategory().toString();
                String storeImageCategoryName = "";
                if(!StringUtils.isEmpty(storeImageCategoryId)){
                    List<Map<String,String>> filterList = mapList.stream().filter(a -> a.get("store_attrib_def_code").equals("store_image_category")).collect(Collectors.toList())
                            .stream().filter(a -> a.get("store_attrib_item_code").equals(storeImageCategoryId)).collect(Collectors.toList());
                    if(!filterList.isEmpty()){
                        storeImageCategoryName = filterList.get(0).get("store_attrib_item_name");
                    }
                }
                imageCategoryCell.setCellValue(storeImageCategoryName);
                String storeInstallVersionId = StringUtils.isEmpty(viewStore.getInstallVersion())?"":viewStore.getInstallVersion().toString();
                String storeInstallVersionName = "";
                if(!StringUtils.isEmpty(storeInstallVersionId)){
                    List<Map<String,String>> filterList = mapList.stream().filter(a -> a.get("store_attrib_def_code").equals("install_version")).collect(Collectors.toList())
                            .stream().filter(a -> a.get("store_attrib_item_code").equals(storeInstallVersionId)).collect(Collectors.toList());
                    if(!filterList.isEmpty()){
                        storeInstallVersionName = filterList.get(0).get("store_attrib_item_name");
                    }
                }
                restoreVersionCell.setCellValue(storeInstallVersionName);
                String storeLogoVersionId = StringUtils.isEmpty(viewStore.getLogoVersion())?"":viewStore.getLogoVersion().toString();
                String storeLogoVersionName = "";
                if(!StringUtils.isEmpty(storeLogoVersionId)){
                    List<Map<String,String>> filterList = mapList.stream().filter(a -> a.get("store_attrib_def_code").equals("LOGO_version")).collect(Collectors.toList())
                            .stream().filter(a -> a.get("store_attrib_item_code").equals(storeLogoVersionId)).collect(Collectors.toList());
                    if(!filterList.isEmpty()){
                        storeLogoVersionName = filterList.get(0).get("store_attrib_item_name");
                    }
                }
                logoVersionCell.setCellValue(storeLogoVersionName);

                String storeCategoryId = StringUtils.isEmpty(viewStore.getStoreCategory())?"":viewStore.getStoreCategory().toString();
                String storeCategoryName = "";
                if(!StringUtils.isEmpty(storeCategoryId)){
                    List<Map<String,String>> filterList = mapList.stream().filter(a -> a.get("store_attrib_def_code").equals("store_category")).collect(Collectors.toList())
                            .stream().filter(a -> a.get("store_attrib_item_code").equals(storeCategoryId)).collect(Collectors.toList());
                    if(!filterList.isEmpty()){
                        storeCategoryName = filterList.get(0).get("store_attrib_item_name");
                    }
                }
                storeCategoryCell.setCellValue(storeCategoryName);
                specialSaleCell.setCellValue(StringUtils.isEmpty(viewStore.getStoreAddress())?"":viewStore.getStoreAddress());
                floorNumCell.setCellValue(StringUtils.isEmpty(viewStore.getFloor())?"":viewStore.getFloor());
                depositCell.setCellValue(viewStore.getDeposit()==null?"0.0000":viewStore.getDeposit().toString());
                rentalCell.setCellValue(StringUtils.isEmpty(viewStore.getRental())?"":viewStore.getRental().toString());
                saleDeductionCell.setCellValue(StringUtils.isEmpty(viewStore.getSalePoint())?"":viewStore.getSalePoint().toString());
                recentRestoreCell.setCellValue(StringUtils.isEmpty(viewStore.getRecentResetTime())?"":DateUtil.date2String(viewStore.getRecentResetTime()));
                restoreStandardCell.setCellValue(StringUtils.isEmpty(viewStore.getDecorationStandard())?"":viewStore.getDecorationStandard());
                restoreYearsCell.setCellValue(StringUtils.isEmpty(viewStore.getDecorationLimitYears())?"":viewStore.getDecorationLimitYears());
                longitudeCell.setCellValue(viewStore.getStoreAddressLongitude()==null?"":viewStore.getStoreAddressLongitude().toString());
                latitudeCell.setCellValue(viewStore.getStoreAddressLatitude()==null?"":viewStore.getStoreAddressLatitude().toString());

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(out != null){
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return workbook;
    }


    public static Workbook getSupplierWorkbook(List<SupplierVO> list){
        OutputStream out = null;
        Workbook workbook = new XSSFWorkbook();
        try {
            Sheet sheet = workbook.createSheet();
            Row firstRow = sheet.createRow(0);
            firstRow.createCell(0).setCellValue("编号");
            firstRow.createCell(1).setCellValue("名称");
            firstRow.createCell(2).setCellValue("名称描述");
            firstRow.createCell(3).setCellValue("供应商类别");
            firstRow.createCell(4).setCellValue("法人");
            firstRow.createCell(5).setCellValue("联系人");
            firstRow.createCell(6).setCellValue("地址");
            firstRow.createCell(7).setCellValue("邮编");
            firstRow.createCell(8).setCellValue("电话");
            firstRow.createCell(9).setCellValue("手机");
            firstRow.createCell(10).setCellValue("国家");
            firstRow.createCell(11).setCellValue("省份");
            firstRow.createCell(12).setCellValue("城市");
            firstRow.createCell(13).setCellValue("电子邮件");
            firstRow.createCell(14).setCellValue("传真");
            firstRow.createCell(15).setCellValue("开户行");
            firstRow.createCell(16).setCellValue("银行帐号");
            firstRow.createCell(17).setCellValue("税号");
            firstRow.createCell(18).setCellValue("公司名称");
            firstRow.createCell(19).setCellValue("状态");

            for(int i=0;i<list.size();i++){
                SupplierVO supplierVO = list.get(i);
                Row itemRow = sheet.createRow(i+1);

                Cell cell0 = itemRow.createCell(0);
                Cell cell1 = itemRow.createCell(1);
                Cell cell2 = itemRow.createCell(2);
                Cell cell3 = itemRow.createCell(3);
                Cell cell4 = itemRow.createCell(4);
                Cell cell5 = itemRow.createCell(5);
                Cell cell6 = itemRow.createCell(6);
                Cell cell7 = itemRow.createCell(7);
                Cell cell8 = itemRow.createCell(8);
                Cell cell9 = itemRow.createCell(9);
                Cell cell10 = itemRow.createCell(10);
                Cell cell11 = itemRow.createCell(11);
                Cell cell12 = itemRow.createCell(12);
                Cell cell13 = itemRow.createCell(13);
                Cell cell14 = itemRow.createCell(14);
                Cell cell15 = itemRow.createCell(15);
                Cell cell16 = itemRow.createCell(16);
                Cell cell17 = itemRow.createCell(17);
                Cell cell18 = itemRow.createCell(18);
                Cell cell19 = itemRow.createCell(19);

                cell0.setCellValue(StringUtils.isEmpty(supplierVO.getSupplierCode())?"":supplierVO.getSupplierCode());
                cell1.setCellValue(StringUtils.isEmpty(supplierVO.getSupplierName())?"":supplierVO.getSupplierName());
                cell2.setCellValue(StringUtils.isEmpty(supplierVO.getSupplierDescription())?"":supplierVO.getSupplierDescription());
                cell3.setCellValue(StringUtils.isEmpty(supplierVO.getSupplierCSuppliertypeId())?"":supplierVO.getSupplierCSuppliertypeId());
                cell4.setCellValue(StringUtils.isEmpty(supplierVO.getSupplierLegalperson())?"":supplierVO.getSupplierLegalperson());
                cell5.setCellValue(StringUtils.isEmpty(supplierVO.getSupplierContacter())?"":supplierVO.getSupplierContacter());
                cell6.setCellValue(StringUtils.isEmpty(supplierVO.getSupplierAddress())?"":supplierVO.getSupplierAddress());
                cell7.setCellValue(StringUtils.isEmpty(supplierVO.getSupplierPostal())?"":supplierVO.getSupplierPostal());
                cell8.setCellValue(StringUtils.isEmpty(supplierVO.getSupplierPhone())?"":supplierVO.getSupplierPhone());
                cell9.setCellValue(StringUtils.isEmpty(supplierVO.getSupplierMobile())?"":supplierVO.getSupplierMobile());
                cell10.setCellValue(StringUtils.isEmpty(supplierVO.getSupplierCCountryId())?"":supplierVO.getSupplierCCountryId());
                cell11.setCellValue(StringUtils.isEmpty(supplierVO.getSupplierCProvinceId())?"":supplierVO.getSupplierCProvinceId());
                cell12.setCellValue(StringUtils.isEmpty(supplierVO.getSupplierCCityId())?"":supplierVO.getSupplierCCityId());
                cell13.setCellValue(StringUtils.isEmpty(supplierVO.getSupplierEmail())?"":supplierVO.getSupplierEmail());
                cell14.setCellValue(StringUtils.isEmpty(supplierVO.getSupplierFax())?"":supplierVO.getSupplierFax());
                cell15.setCellValue(StringUtils.isEmpty(supplierVO.getSupplierDepositBank())?"":supplierVO.getSupplierDepositBank());
                cell16.setCellValue(StringUtils.isEmpty(supplierVO.getSupplierAccount())?"":supplierVO.getSupplierAccount());
                cell17.setCellValue(StringUtils.isEmpty(supplierVO.getSupplierTaxno())?"":supplierVO.getSupplierTaxno());
                cell18.setCellValue(StringUtils.isEmpty(supplierVO.getSupplierCompanyname())?"":supplierVO.getSupplierCompanyname());
                cell19.setCellValue(StringUtils.isEmpty(supplierVO.getSupplierDataStatusName())?"":supplierVO.getSupplierDataStatusName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(out != null){
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return workbook;
    }

    public static Workbook getStaffWorkbook(List<StaffVO> list){
        OutputStream out = null;
        Workbook workbook = new XSSFWorkbook();
        try {
            Sheet sheet = workbook.createSheet();
            Row firstRow = sheet.createRow(0);

            firstRow.createCell(0).setCellValue("员工编号");
            firstRow.createCell(1).setCellValue("中文名");
            firstRow.createCell(2).setCellValue("成本中心");
            firstRow.createCell(3).setCellValue("证件类型");
            firstRow.createCell(4).setCellValue("证件编号");
            firstRow.createCell(5).setCellValue("出生日期");
            firstRow.createCell(6).setCellValue("手机号码");
            firstRow.createCell(7).setCellValue("邮箱");
            firstRow.createCell(8).setCellValue("电话");
            firstRow.createCell(9).setCellValue("分机号");
            firstRow.createCell(10).setCellValue("门禁卡号");
            firstRow.createCell(11).setCellValue("员工状态");
            firstRow.createCell(12).setCellValue("离职日期");
            firstRow.createCell(13).setCellValue("是否成本中心负责人");
            firstRow.createCell(14).setCellValue("岗位");
            firstRow.createCell(15).setCellValue("职级");
            firstRow.createCell(16).setCellValue("所属公司");
            firstRow.createCell(17).setCellValue("直接上级");
            firstRow.createCell(18).setCellValue("办公地点");
            firstRow.createCell(19).setCellValue("员工编制");
            firstRow.createCell(20).setCellValue("部门");
            firstRow.createCell(21).setCellValue("岗位序列");
            firstRow.createCell(22).setCellValue("人员类型");
            firstRow.createCell(23).setCellValue("国籍");
            firstRow.createCell(24).setCellValue("民族");

            for(int i=0;i<list.size();i++){
                StaffVO staffVO = list.get(i);
                Row itemRow = sheet.createRow(i+1);

                Cell cell0 = itemRow.createCell(0);
                Cell cell1 = itemRow.createCell(1);
                Cell cell2 = itemRow.createCell(2);
                Cell cell3 = itemRow.createCell(3);
                Cell cell4 = itemRow.createCell(4);
                Cell cell5 = itemRow.createCell(5);
                Cell cell6 = itemRow.createCell(6);
                Cell cell7 = itemRow.createCell(7);
                Cell cell8 = itemRow.createCell(8);
                Cell cell9 = itemRow.createCell(9);
                Cell cell10 = itemRow.createCell(10);
                Cell cell11 = itemRow.createCell(11);
                Cell cell12 = itemRow.createCell(12);
                Cell cell13 = itemRow.createCell(13);
                Cell cell14 = itemRow.createCell(14);
                Cell cell15 = itemRow.createCell(15);
                Cell cell16 = itemRow.createCell(16);
                Cell cell17 = itemRow.createCell(17);
                Cell cell18 = itemRow.createCell(18);
                Cell cell19 = itemRow.createCell(19);
                Cell cell20 = itemRow.createCell(20);
                Cell cell21 = itemRow.createCell(21);
                Cell cell22 = itemRow.createCell(22);
                Cell cell23 = itemRow.createCell(23);
                Cell cell24 = itemRow.createCell(24);

                cell0.setCellValue(StringUtils.isEmpty(staffVO.getStaffNum())?"":staffVO.getStaffNum());
                cell1.setCellValue(StringUtils.isEmpty(staffVO.getStaffNameLocal())?"":staffVO.getStaffNameLocal());
                cell2.setCellValue(StringUtils.isEmpty(staffVO.getStaffNum())?"":staffVO.getCostCenterId());
                cell3.setCellValue(StringUtils.isEmpty(staffVO.getCredentialType())?"":staffVO.getCredentialType());
                cell4.setCellValue(StringUtils.isEmpty(staffVO.getCredentialNum())?"":staffVO.getCredentialNum());
                cell5.setCellValue(StringUtils.isEmpty(staffVO.getBirthday())?"":DateUtil.date2String(staffVO.getBirthday()));
                cell6.setCellValue(StringUtils.isEmpty(staffVO.getMobile())?"":staffVO.getMobile());
                cell7.setCellValue(StringUtils.isEmpty(staffVO.getEmail())?"":staffVO.getEmail());
                cell8.setCellValue(StringUtils.isEmpty(staffVO.getPhone())?"":staffVO.getPhone());
                cell9.setCellValue(StringUtils.isEmpty(staffVO.getExtnum())?"":staffVO.getExtnum());
                cell10.setCellValue(StringUtils.isEmpty(staffVO.getDoorcontrolnum())?"":staffVO.getDoorcontrolnum());
                cell11.setCellValue(StringUtils.isEmpty(staffVO.getStaffState())?"":staffVO.getStaffState());
                cell12.setCellValue(StringUtils.isEmpty(staffVO.getDemissionDate())?"":DateUtil.date2String(staffVO.getDemissionDate()));
                cell13.setCellValue(StringUtils.isEmpty(staffVO.getIsDirector())?"":staffVO.getIsDirector());
                cell14.setCellValue(StringUtils.isEmpty(staffVO.getPostName())?"":staffVO.getPostName());
                cell15.setCellValue(StringUtils.isEmpty(staffVO.getStaffLevel())?"":staffVO.getStaffLevel());
                cell16.setCellValue(StringUtils.isEmpty(staffVO.getCompany())?"":staffVO.getCompany());
                cell17.setCellValue(StringUtils.isEmpty(staffVO.getSuperiorNum())?"":staffVO.getSuperiorNum());
                cell18.setCellValue(StringUtils.isEmpty(staffVO.getOfficeLocation())?"":staffVO.getOfficeLocation());
                cell19.setCellValue(StringUtils.isEmpty(staffVO.getLeague())?"":staffVO.getLeague());
                cell20.setCellValue(StringUtils.isEmpty(staffVO.getDepartmentId())?"":staffVO.getDepartmentId());
                cell21.setCellValue(StringUtils.isEmpty(staffVO.getJobSequence())?"":staffVO.getJobSequence());
                cell22.setCellValue(StringUtils.isEmpty(staffVO.getStaffType())?"":staffVO.getStaffType());
                cell23.setCellValue(StringUtils.isEmpty(staffVO.getNationality())?"":staffVO.getNationality());
                cell24.setCellValue(StringUtils.isEmpty(staffVO.getEthnic())?"":staffVO.getEthnic());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(out != null){
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return workbook;
    }



    public static Workbook getPostWorkbook(List<PostVO> list){
        OutputStream out = null;
        Workbook workbook = new XSSFWorkbook();
        try {
            Sheet sheet = workbook.createSheet();
            Row firstRow = sheet.createRow(0);

            firstRow.createCell(0).setCellValue("岗位编号");
            firstRow.createCell(1).setCellValue("语言");
            firstRow.createCell(2).setCellValue("岗位名称");
            firstRow.createCell(3).setCellValue("部门名称");
            firstRow.createCell(4).setCellValue("上级岗位");
            firstRow.createCell(5).setCellValue("负责人");

            for(int i=0;i<list.size();i++){
                PostVO postVO = list.get(i);
                Row itemRow = sheet.createRow(i+1);

                Cell cell0 = itemRow.createCell(0);
                Cell cell1 = itemRow.createCell(1);
                Cell cell2 = itemRow.createCell(2);
                Cell cell3 = itemRow.createCell(3);
                Cell cell4 = itemRow.createCell(4);
                Cell cell5 = itemRow.createCell(5);

                cell0.setCellValue(StringUtils.isEmpty(postVO.getPostId())?"":postVO.getPostId());
                cell1.setCellValue(StringUtils.isEmpty(postVO.getLanCode())?"":postVO.getLanCode());
                cell2.setCellValue(StringUtils.isEmpty(postVO.getPostName())?"":postVO.getPostName());
                cell3.setCellValue(StringUtils.isEmpty(postVO.getDepartmentId())?"":postVO.getDepartmentId());
                cell4.setCellValue(StringUtils.isEmpty(postVO.getPaPostId())?"":postVO.getPaPostId());
                cell5.setCellValue(StringUtils.isEmpty(postVO.getCharger())?"":postVO.getCharger());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(out != null){
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return workbook;
    }

    public static Workbook getCostcenterWorkbook(List<CostcenterVO> list){
        OutputStream out = null;
        Workbook workbook = new XSSFWorkbook();
        try {
            Sheet sheet = workbook.createSheet();
            Row firstRow = sheet.createRow(0);

            firstRow.createCell(0).setCellValue("成本中心编号");
            firstRow.createCell(1).setCellValue("语言");
            firstRow.createCell(2).setCellValue("成本中心名称");
            firstRow.createCell(3).setCellValue("负责人");
            firstRow.createCell(4).setCellValue("部门");
            firstRow.createCell(5).setCellValue("总监");
            firstRow.createCell(6).setCellValue("副总");
            firstRow.createCell(7).setCellValue("成本中心描述");

            for(int i=0;i<list.size();i++){
                CostcenterVO costcenterVO = list.get(i);
                Row itemRow = sheet.createRow(i+1);

                Cell cell0 = itemRow.createCell(0);
                Cell cell1 = itemRow.createCell(1);
                Cell cell2 = itemRow.createCell(2);
                Cell cell3 = itemRow.createCell(3);
                Cell cell4 = itemRow.createCell(4);
                Cell cell5 = itemRow.createCell(5);
                Cell cell6 = itemRow.createCell(6);
                Cell cell7 = itemRow.createCell(7);

                cell0.setCellValue(StringUtils.isEmpty(costcenterVO.getCostcenterId())?"":costcenterVO.getCostcenterId());
                cell1.setCellValue(StringUtils.isEmpty(costcenterVO.getLanCode())?"":costcenterVO.getLanCode());
                cell2.setCellValue(StringUtils.isEmpty(costcenterVO.getCostCenterName())?"":costcenterVO.getCostCenterName());
                cell3.setCellValue(StringUtils.isEmpty(costcenterVO.getCostcenterManager())?"":costcenterVO.getCostcenterManager());
                cell4.setCellValue(StringUtils.isEmpty(costcenterVO.getDepartmentId())?"":costcenterVO.getDepartmentId());
                cell5.setCellValue(StringUtils.isEmpty(costcenterVO.getCostcenterChief())?"":costcenterVO.getCostcenterChief());
                cell6.setCellValue(StringUtils.isEmpty(costcenterVO.getCostcenterVp())?"":costcenterVO.getCostcenterVp());
                cell7.setCellValue(StringUtils.isEmpty(costcenterVO.getCostcenterDesc())?"":costcenterVO.getCostcenterDesc());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(out != null){
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return workbook;
    }

    public static Workbook getRespcenterWorkbook(List<RespCenterVO> list){
        OutputStream out = null;
        Workbook workbook = new XSSFWorkbook();
        try {
            Sheet sheet = workbook.createSheet();
            Row firstRow = sheet.createRow(0);
            firstRow.createCell(0).setCellValue("责任中心编号");
            firstRow.createCell(1).setCellValue("语言");
            firstRow.createCell(2).setCellValue("责任中心名称");
            firstRow.createCell(3).setCellValue("负责人");
            firstRow.createCell(4).setCellValue("描述");
            firstRow.createCell(5).setCellValue("生效时间");
            firstRow.createCell(6).setCellValue("状态");
            for(int i=0;i<list.size();i++){
                RespCenterVO respCenterVO = list.get(i);
                Row itemRow = sheet.createRow(i+1);
                Cell cell0 = itemRow.createCell(0);
                Cell cell1 = itemRow.createCell(1);
                Cell cell2 = itemRow.createCell(2);
                Cell cell3 = itemRow.createCell(3);
                Cell cell4 = itemRow.createCell(4);
                Cell cell5 = itemRow.createCell(5);
                Cell cell6 = itemRow.createCell(6);
                cell0.setCellValue(StringUtils.isEmpty(respCenterVO.getRespcenterId())?"":respCenterVO.getRespcenterId());
                cell1.setCellValue(StringUtils.isEmpty(respCenterVO.getLanCode())?"":respCenterVO.getLanCode());
                cell2.setCellValue(StringUtils.isEmpty(respCenterVO.getRespcenterName())?"":respCenterVO.getRespcenterName());
                cell3.setCellValue(StringUtils.isEmpty(respCenterVO.getRespcenterManager())?"":respCenterVO.getRespcenterName());
                cell4.setCellValue(StringUtils.isEmpty(respCenterVO.getDepartmentId())?"":respCenterVO.getDepartmentId());
                cell5.setCellValue(StringUtils.isEmpty(respCenterVO.getRespcenterState())?"":respCenterVO.getRespcenterState());
                cell6.setCellValue(respCenterVO.getEffectiveDate()==null?"":DateUtil.date2String(respCenterVO.getEffectiveDate()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(out != null){
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return workbook;
    }

    public static Workbook getFabiricWorkbook(List<FabricVO> list){
        OutputStream out = null;
        Workbook workbook = new XSSFWorkbook();
        try {
            Sheet sheet = workbook.createSheet();
            Row firstRow = sheet.createRow(0);
            firstRow.createCell(0).setCellValue("面料编号");
            firstRow.createCell(1).setCellValue("面料名称");
            for(int i=0;i<list.size();i++){
                FabricVO fabricVO = list.get(i);
                Row itemRow = sheet.createRow(i+1);
                Cell cell0 = itemRow.createCell(0);
                Cell cell1 = itemRow.createCell(1);
                cell0.setCellValue(StringUtils.isEmpty(fabricVO.getFabricCode())?"":fabricVO.getFabricCode());
                cell1.setCellValue(StringUtils.isEmpty(fabricVO.getFabricName())?"":fabricVO.getFabricName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(out != null){
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return workbook;
    }

    public static Workbook getExWorkbook(List<ExpenditureCategoryVO> list){
        OutputStream out = null;
        Workbook workbook = new XSSFWorkbook();
        try {
            Sheet sheet = workbook.createSheet();
            Row firstRow = sheet.createRow(0);
            firstRow.createCell(0).setCellValue("开支类别编号");
            firstRow.createCell(1).setCellValue("语言");
            firstRow.createCell(2).setCellValue("开支类别名称");
            firstRow.createCell(3).setCellValue("成本中心");
            firstRow.createCell(4).setCellValue("描述");
            firstRow.createCell(5).setCellValue("状态");
            firstRow.createCell(6).setCellValue("生效时间");
            for(int i=0;i<list.size();i++){
                ExpenditureCategoryVO expenditureCategoryVO = list.get(i);
                Row itemRow = sheet.createRow(i+1);
                Cell cell0 = itemRow.createCell(0);
                Cell cell1 = itemRow.createCell(1);
                Cell cell2 = itemRow.createCell(2);
                Cell cell3 = itemRow.createCell(3);
                Cell cell4 = itemRow.createCell(4);
                Cell cell5 = itemRow.createCell(5);
                Cell cell6 = itemRow.createCell(6);
                cell0.setCellValue(StringUtils.isEmpty(expenditureCategoryVO.getExcategoryId())?"":expenditureCategoryVO.getExcategoryId());
                cell1.setCellValue(StringUtils.isEmpty(expenditureCategoryVO.getLanCode())?"":expenditureCategoryVO.getLanCode());
                cell2.setCellValue(StringUtils.isEmpty(expenditureCategoryVO.getExcategoryName())?"":expenditureCategoryVO.getExcategoryName());
                cell3.setCellValue(StringUtils.isEmpty(expenditureCategoryVO.getCostcenterId())?"":expenditureCategoryVO.getCostcenterId());
                cell4.setCellValue(StringUtils.isEmpty(expenditureCategoryVO.getExcategoryDesc())?"":expenditureCategoryVO.getExcategoryDesc());
                cell5.setCellValue(StringUtils.isEmpty(expenditureCategoryVO.getExcategoryState())?"":expenditureCategoryVO.getExcategoryState());
                cell6.setCellValue(expenditureCategoryVO.getEffectiveDate()==null?"":DateUtil.date2String(expenditureCategoryVO.getEffectiveDate()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(out != null){
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return workbook;
    }
    public static Workbook getCostumeWorkbook(List<Object> selectList){
        OutputStream out = null;
        Workbook workbook = new XSSFWorkbook();
        try {
            Sheet sheet = workbook.createSheet();
            Row firstRow = sheet.createRow(0);
            Row secondRow = sheet.createRow(1);
            firstRow.createCell(0).setCellValue("预估倍率");
            firstRow.createCell(1).setCellValue("订单序号");
            firstRow.createCell(2).setCellValue("开发号");
            firstRow.createCell(3).setCellValue("款型号");
            firstRow.createCell(4).setCellValue("样板号");
            firstRow.createCell(5).setCellValue("线");
            firstRow.createCell(6).setCellValue("SKU");
            firstRow.createCell(7).setCellValue("面料编号");
            firstRow.createCell(8).setCellValue("面料名");
            firstRow.createCell(9).setCellValue("品名");
            firstRow.createCell(10).setCellValue("花色");
            firstRow.createCell(11).setCellValue("零售价");
            firstRow.createCell(12).setCellValue("品种");
            firstRow.createCell(13).setCellValue("年份");
            firstRow.createCell(14).setCellValue("季节");
            firstRow.createCell(15).setCellValue("开发季");
            firstRow.createCell(16).setCellValue("波");
            firstRow.createCell(17).setCellValue("段");
            firstRow.createCell(18).setCellValue("组别");
            firstRow.createCell(19).setCellValue("上市日期");
            firstRow.createCell(20).setCellValue("色卡编号");
            firstRow.createCell(21).setCellValue("色卡名");
            firstRow.createCell(22).setCellValue("颜色（色名）");
            firstRow.createCell(23).setCellValue("工作组");
            firstRow.createCell(24).setCellValue("尺寸组");
            firstRow.createCell(25).setCellValue("品牌");
            firstRow.createCell(26).setCellValue("统一编码");
            firstRow.createCell(27).setCellValue("规格");
            firstRow.createCell(28).setCellValue("批次");
            firstRow.createCell(29).setCellValue("单位");
            firstRow.createCell(30).setCellValue("款号规则");
            firstRow.createCell(31).setCellValue("建档人");
            firstRow.createCell(32).setCellValue("建档日期");
            firstRow.createCell(33).setCellValue("供应商编号");
            firstRow.createCell(34).setCellValue("预估成本");
            firstRow.createCell(35).setCellValue("商品小类");
            firstRow.createCell(36).setCellValue("内部面料名");

            secondRow.createCell(1).setCellValue("4890");
            secondRow.createCell(2).setCellValue("0918A4890");
            secondRow.createCell(3).setCellValue("4001");
            secondRow.createCell(4).setCellValue("4001");
            secondRow.createCell(5).setCellValue("09");
            secondRow.createCell(6).setCellValue("AAA0918B48909H");
            secondRow.createCell(7).setCellValue("YRN00017");
            secondRow.createCell(8).setCellValue("超细羊毛");
            secondRow.createCell(9).setCellValue("精品服饰");
            secondRow.createCell(10).setCellValue("白色");
            secondRow.createCell(11).setCellValue("4420");
            secondRow.createCell(12).setCellValue("针织衫");
            secondRow.createCell(13).setCellValue("2019年");
            secondRow.createCell(14).setCellValue("春季");
            secondRow.createCell(15).setCellValue("春夏季");
            secondRow.createCell(16).setCellValue("1月");
            secondRow.createCell(17).setCellValue("第1周");
            secondRow.createCell(18).setCellValue("第一组");
            secondRow.createCell(19).setCellValue("2018-5-7");
            secondRow.createCell(20).setCellValue("110601");
            secondRow.createCell(21).setCellValue("110601");
            secondRow.createCell(22).setCellValue("象牙白");
            secondRow.createCell(23).setCellValue("剪刀组");
            secondRow.createCell(24).setCellValue("样衣");
            secondRow.createCell(25).setCellValue("ICICLE");
            secondRow.createCell(26).setCellValue("AA");
            secondRow.createCell(27).setCellValue("标准版");
            secondRow.createCell(28).setCellValue("第一批");
            secondRow.createCell(29).setCellValue("件");
            secondRow.createCell(30).setCellValue("2012年款号规则");
            secondRow.createCell(31).setCellValue("admin");
            secondRow.createCell(32).setCellValue("2019-10-10");
            secondRow.createCell(35).setCellValue("套衫");

            CellStyle fontStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setColor(Font.COLOR_RED);
            fontStyle.setFont(font);
            for(int i=0;i<=36;i++){
                Cell secondRowCell = secondRow.getCell(i);
                if(secondRowCell!=null){
                    secondRowCell.setCellStyle(fontStyle);
                }
            }

            CellStyle cellstyle = workbook.createCellStyle();
            cellstyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
            cellstyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
            for(int i = 25;i<32;i++){
                secondRow.getCell(i).setCellStyle(cellstyle);
            }
            List<String> costumeCategoryLevel1List = (List<String>)selectList.get(0);
            List<String> costumeCategoryLevel2List = (List<String>)selectList.get(1);
            List<Map<String,String>> selectItemList = (List<Map<String,String>>)selectList.get(2);

            List<String> lineList = getItemList(selectItemList,"select_line");
            List<String> natureSeasonList = getItemList(selectItemList,"select_nature_season");
            List<String> devSeasonList = getItemList(selectItemList,"select_dev_season");
            List<String> waveList = getItemList(selectItemList,"select_wave");
            List<String> bandList = getItemList(selectItemList,"select_band");
            List<String> groupList = getItemList(selectItemList,"select_group");
            List<String> workGroupList = getItemList(selectItemList,"select_work_group");
            List<String> sizeGroupList = getItemList(selectItemList,"select_size_group");
            List<String> brandList = getItemList(selectItemList,"select_brand");
            List<String> standardList = getItemList(selectItemList,"select_standard");
            List<String> batchList = getItemList(selectItemList,"select_batch");
            List<String> codeStandardList = getItemList(selectItemList,"select_code_standard");
            List<String> uomList = getItemList(selectItemList,"select_uom");

            int startRow = 1;
            int endRow = 1000;
            setDropdownList(costumeCategoryLevel1List.toArray(new String[costumeCategoryLevel1List.size()]),startRow,endRow,12,12,sheet);
            setDropdownList(getYearList().toArray(new String[getYearList().size()]),startRow,endRow,13,13,sheet);
            setDropdownList(natureSeasonList.toArray(new String[natureSeasonList.size()]),startRow,endRow,14,14,sheet);
            setDropdownList(devSeasonList.toArray(new String[devSeasonList.size()]),startRow,endRow,15,15,sheet);
            setDropdownList(waveList.toArray(new String[waveList.size()]),startRow,endRow,16,16,sheet);
            setDropdownList(bandList.toArray(new String[bandList.size()]),startRow,endRow,17,17,sheet);
            setDropdownList(groupList.toArray(new String[groupList.size()]),startRow,endRow,18,18,sheet);
            setDropdownList(workGroupList.toArray(new String[workGroupList.size()]),startRow,endRow,23,23,sheet);
            setDropdownList(sizeGroupList.toArray(new String[sizeGroupList.size()]),startRow,endRow,24,24,sheet);
            setDropdownList(brandList.toArray(new String[brandList.size()]),startRow,endRow,25,25,sheet);
            setDropdownList(standardList.toArray(new String[standardList.size()]),startRow,endRow,27,27,sheet);
            setDropdownList(batchList.toArray(new String[batchList.size()]),startRow,endRow,28,28,sheet);
            setDropdownList(uomList.toArray(new String[uomList.size()]),startRow,endRow,29,29,sheet);
            setDropdownList(codeStandardList.toArray(new String[codeStandardList.size()]),startRow,endRow,30,30,sheet);

            String productKindSheetName = "productKindSecond";
            Sheet kindHiddenSheet = workbook.createSheet(productKindSheetName);
            workbook.setSheetHidden(workbook.getSheetIndex(kindHiddenSheet), true);
            setRefercenceList(kindHiddenSheet,sheet,productKindSheetName,costumeCategoryLevel2List,startRow,endRow,35,35,0);

            String productLineSheetName = "line";
            Sheet lineHiddenSheet = workbook.createSheet(productLineSheetName);
            workbook.setSheetHidden(workbook.getSheetIndex(lineHiddenSheet), true);
            setRefercenceList(lineHiddenSheet,sheet,productLineSheetName,lineList,startRow,endRow,5,5,0);

        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(out != null){
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return workbook;
    }

    public static List<String> getItemList(List<Map<String,String>> selectItemList,String tableName){
        List<String> itemList = new ArrayList<>();
        if(!ListUtil.isBlank(selectItemList) && !StringUtils.isEmpty(tableName)){
            List<Map<String,String>> itemFilterMapList = selectItemList.stream().filter(a->a.get("table_name").equals(tableName)).collect(Collectors.toList());
            if(tableName.equals("select_line")){
                for(Map<String,String> map : itemFilterMapList){
                    itemList.add(map.get("item_key"));
                }
            }else{
                for(Map<String,String> map : itemFilterMapList){
                    itemList.add(map.get("item_value"));
                }
            }
        }
        return itemList;
    }
    public static Workbook getItemDataWorkbook(List<SelectItemVO> list){
        OutputStream out = null;
        Workbook workbook = new XSSFWorkbook();
        try {
            Sheet sheet = workbook.createSheet();
            Row firstRow = sheet.createRow(0);
            firstRow.createCell(0).setCellValue("编号");
            firstRow.createCell(1).setCellValue("表名");
            firstRow.createCell(2).setCellValue("选项类别");
            firstRow.createCell(3).setCellValue("选项键");
            firstRow.createCell(4).setCellValue("选项值");
            firstRow.createCell(5).setCellValue("语言");
            firstRow.createCell(6).setCellValue("状态");
            for(int i=0;i<list.size();i++){
                SelectItemVO selectItemVO = list.get(i);
                Row itemRow = sheet.createRow(i+1);
                Cell cell0 = itemRow.createCell(0);
                Cell cell1 = itemRow.createCell(1);
                Cell cell2 = itemRow.createCell(2);
                Cell cell3 = itemRow.createCell(3);
                Cell cell4 = itemRow.createCell(4);
                Cell cell5 = itemRow.createCell(5);
                Cell cell6 = itemRow.createCell(6);
                cell0.setCellValue(StringUtils.isEmpty(selectItemVO.getId())?"":selectItemVO.getId());
                cell1.setCellValue(StringUtils.isEmpty(selectItemVO.getTableName())?"":selectItemVO.getTableName());
                cell2.setCellValue(StringUtils.isEmpty(selectItemVO.getDescription())?"":selectItemVO.getDescription());
                cell3.setCellValue(StringUtils.isEmpty(selectItemVO.getItemKey())?"":selectItemVO.getItemKey());
                cell4.setCellValue(StringUtils.isEmpty(selectItemVO.getItemValue())?"":selectItemVO.getItemValue());
                cell5.setCellValue(StringUtils.isEmpty(selectItemVO.getLanguage())?"":selectItemVO.getLanguage());
                cell6.setCellValue(StringUtils.isEmpty(selectItemVO.getStatus())?"":selectItemVO.getStatus());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(out != null){
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return workbook;
    }

    public static Workbook getColorCardWorkbook(List<ColorCardVO> list){
        OutputStream out = null;
        Workbook workbook = new XSSFWorkbook();
        try {
            Sheet sheet = workbook.createSheet();
            Row firstRow = sheet.createRow(0);
            firstRow.createCell(0).setCellValue("色卡编号");
            firstRow.createCell(1).setCellValue("色卡名称");
            firstRow.createCell(2).setCellValue("状态");
            firstRow.createCell(3).setCellValue("语言");
            firstRow.createCell(4).setCellValue("创建时间");
            firstRow.createCell(5).setCellValue("更新时间");
            for(int i=0;i<list.size();i++){
                ColorCardVO colorCardVO = list.get(i);
                Row itemRow = sheet.createRow(i+1);
                Cell cell0 = itemRow.createCell(0);
                Cell cell1 = itemRow.createCell(1);
                Cell cell2 = itemRow.createCell(2);
                Cell cell3 = itemRow.createCell(3);
                Cell cell4 = itemRow.createCell(4);
                Cell cell5 = itemRow.createCell(5);
                cell0.setCellValue(StringUtils.isEmpty(colorCardVO.getColorCardCode())?"":colorCardVO.getColorCardCode());
                cell1.setCellValue(StringUtils.isEmpty(colorCardVO.getColorCardName())?"":colorCardVO.getColorCardName());
                cell2.setCellValue(StringUtils.isEmpty(colorCardVO.getStatus())?"":colorCardVO.getStatus());
                cell3.setCellValue(StringUtils.isEmpty(colorCardVO.getLanguage())?"":colorCardVO.getLanguage());
                cell4.setCellValue(colorCardVO.getCreatedDate()==null?"":DateUtil.date2String(colorCardVO.getCreatedDate()));
                cell5.setCellValue(colorCardVO.getLastUpdateDate()==null?"":DateUtil.date2String(colorCardVO.getLastUpdateDate()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(out != null){
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return workbook;
    }

    public static Workbook getMaterialsWorkbook(List<MaterialVO> list){
        OutputStream out = null;
        Workbook workbook = new XSSFWorkbook();
        try {
            Sheet sheet = workbook.createSheet();
            Row firstRow = sheet.createRow(0);
            firstRow.createCell(0).setCellValue("物料编码");
            firstRow.createCell(1).setCellValue("物料名称");
            firstRow.createCell(2).setCellValue("所属大类");
            firstRow.createCell(3).setCellValue("所属中类");
            firstRow.createCell(4).setCellValue("所属小类");
            firstRow.createCell(5).setCellValue("物料类型");
            firstRow.createCell(6).setCellValue("单位");
            firstRow.createCell(7).setCellValue("物料描述");
            firstRow.createCell(8).setCellValue("样品价格");
            firstRow.createCell(9).setCellValue("大货价格");
            firstRow.createCell(10).setCellValue("币种");
            firstRow.createCell(11).setCellValue("状态");
            for(int i=0;i<list.size();i++){
                MaterialVO materialVO = list.get(i);
                Row itemRow = sheet.createRow(i+1);
                Cell cell0 = itemRow.createCell(0);
                Cell cell1 = itemRow.createCell(1);
                Cell cell2 = itemRow.createCell(2);
                Cell cell3 = itemRow.createCell(3);
                Cell cell4 = itemRow.createCell(4);
                Cell cell5 = itemRow.createCell(5);
                Cell cell6 = itemRow.createCell(6);
                Cell cell7 = itemRow.createCell(7);
                Cell cell8 = itemRow.createCell(8);
                Cell cell9 = itemRow.createCell(9);
                Cell cell10 = itemRow.createCell(10);
                Cell cell11 = itemRow.createCell(11);
                cell0.setCellValue(StringUtils.isEmpty(materialVO.getMaterialCode())?"":materialVO.getMaterialCode());
                cell1.setCellValue(StringUtils.isEmpty(materialVO.getMaterialName())?"":materialVO.getMaterialName());
                cell2.setCellValue(StringUtils.isEmpty(materialVO.getCateDl())?"":materialVO.getCateDl());
                cell3.setCellValue(StringUtils.isEmpty(materialVO.getCateZl())?"":materialVO.getCateZl());
                cell4.setCellValue(StringUtils.isEmpty(materialVO.getCateXl())?"":materialVO.getCateXl());
                cell5.setCellValue(StringUtils.isEmpty(materialVO.getMaterialType())?"":materialVO.getMaterialType());
                cell6.setCellValue(StringUtils.isEmpty(materialVO.getUom())?"":materialVO.getUom());
                cell7.setCellValue(StringUtils.isEmpty(materialVO.getMaterialDesc())?"":materialVO.getMaterialDesc());
                cell8.setCellValue(StringUtils.isEmpty(materialVO.getVendorPrice())?"":materialVO.getVendorPrice());
                cell9.setCellValue(StringUtils.isEmpty(materialVO.getUnitPrice())?"":materialVO.getUnitPrice());
                cell10.setCellValue(StringUtils.isEmpty(materialVO.getCurrencyName())?"":materialVO.getCurrencyName());
                cell11.setCellValue(StringUtils.isEmpty(materialVO.getSyncStatus())?"":materialVO.getSyncStatus());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(out != null){
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return workbook;
    }

    public static Workbook getSubExWorkbook(List<SubExpenditureCategoryVO> list){
        OutputStream out = null;
        Workbook workbook = new XSSFWorkbook();
        try {
            Sheet sheet = workbook.createSheet();
            Row firstRow = sheet.createRow(0);

            firstRow.createCell(0).setCellValue("开支子类别编号");
            firstRow.createCell(1).setCellValue("语言");
            firstRow.createCell(2).setCellValue("开支子类名称");
            firstRow.createCell(3).setCellValue("成本中心");
            firstRow.createCell(4).setCellValue("描述");
            firstRow.createCell(5).setCellValue("状态");
            firstRow.createCell(6).setCellValue("生效时间");
            for(int i=0;i<list.size();i++){
                SubExpenditureCategoryVO subExpenditureCategoryVO = list.get(i);
                Row itemRow = sheet.createRow(i+1);
                Cell cell0 = itemRow.createCell(0);
                Cell cell1 = itemRow.createCell(1);
                Cell cell2 = itemRow.createCell(2);
                Cell cell3 = itemRow.createCell(3);
                Cell cell4 = itemRow.createCell(4);
                Cell cell5 = itemRow.createCell(5);
                Cell cell6 = itemRow.createCell(6);

                cell0.setCellValue(StringUtils.isEmpty(subExpenditureCategoryVO.getSubExcategoryId())?"":subExpenditureCategoryVO.getSubExcategoryId());
                cell1.setCellValue(StringUtils.isEmpty(subExpenditureCategoryVO.getLanCode())?"":subExpenditureCategoryVO.getLanCode());
                cell2.setCellValue(StringUtils.isEmpty(subExpenditureCategoryVO.getSubExcategoryName())?"":subExpenditureCategoryVO.getSubExcategoryName());
                cell3.setCellValue(StringUtils.isEmpty(subExpenditureCategoryVO.getCostcenterId())?"":subExpenditureCategoryVO.getCostcenterId());
                cell4.setCellValue(StringUtils.isEmpty(subExpenditureCategoryVO.getSubExcategoryDesc())?"":subExpenditureCategoryVO.getSubExcategoryDesc());
                cell5.setCellValue(StringUtils.isEmpty(subExpenditureCategoryVO.getSubExcategoryState())?"":subExpenditureCategoryVO.getSubExcategoryState());
                cell6.setCellValue(subExpenditureCategoryVO.getEffectiveDate()==null?"":DateUtil.date2String(subExpenditureCategoryVO.getEffectiveDate()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(out != null){
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return workbook;
    }


    public static Workbook getBuyerWorkbook(List<BuyerVO> list){
        OutputStream out = null;
        Workbook workbook = new XSSFWorkbook();
        try {
            Sheet sheet = workbook.createSheet();
            Row firstRow = sheet.createRow(0);

            firstRow.createCell(0).setCellValue("客户ID");
            firstRow.createCell(1).setCellValue("客户名");
            firstRow.createCell(2).setCellValue("客户简称");
            firstRow.createCell(3).setCellValue("全名");
            firstRow.createCell(4).setCellValue("拼音首字母简写");
            firstRow.createCell(5).setCellValue("所在城市");
            firstRow.createCell(6).setCellValue("代理商级别");
            firstRow.createCell(7).setCellValue("客户状态");
            firstRow.createCell(8).setCellValue("联系人");
            firstRow.createCell(9).setCellValue("联系电话");
            firstRow.createCell(10).setCellValue("手机");
            firstRow.createCell(11).setCellValue("Email");
            firstRow.createCell(12).setCellValue("传真");
            firstRow.createCell(13).setCellValue("加盟费");
            firstRow.createCell(14).setCellValue("合约开始时间");
            firstRow.createCell(15).setCellValue("合约结束时间");
            firstRow.createCell(16).setCellValue("法人");
            firstRow.createCell(17).setCellValue("公司名称");
            firstRow.createCell(18).setCellValue("开户行");
            firstRow.createCell(19).setCellValue("银行账号");
            firstRow.createCell(20).setCellValue("税号");
            firstRow.createCell(21).setCellValue("地址");
            firstRow.createCell(22).setCellValue("备注");
            firstRow.createCell(23).setCellValue("现场管理中心");

            for(int i=0;i<list.size();i++){
                BuyerVO buyerVO = list.get(i);
                Row itemRow = sheet.createRow(i+1);

                Cell cell0 = itemRow.createCell(0);
                Cell cell1 = itemRow.createCell(1);
                Cell cell2 = itemRow.createCell(2);
                Cell cell3 = itemRow.createCell(3);
                Cell cell4 = itemRow.createCell(4);
                Cell cell5 = itemRow.createCell(5);
                Cell cell6 = itemRow.createCell(6);
                Cell cell7 = itemRow.createCell(7);
                Cell cell8 = itemRow.createCell(8);
                Cell cell9 = itemRow.createCell(9);
                Cell cell10 = itemRow.createCell(10);
                Cell cell11 = itemRow.createCell(11);
                Cell cell12 = itemRow.createCell(12);
                Cell cell13 = itemRow.createCell(13);
                Cell cell14 = itemRow.createCell(14);
                Cell cell15 = itemRow.createCell(15);
                Cell cell16 = itemRow.createCell(16);
                Cell cell17 = itemRow.createCell(17);
                Cell cell18 = itemRow.createCell(18);
                Cell cell19 = itemRow.createCell(19);
                Cell cell20 = itemRow.createCell(20);
                Cell cell21 = itemRow.createCell(21);
                Cell cell22 = itemRow.createCell(22);
                Cell cell23 = itemRow.createCell(23);

                cell0.setCellValue(StringUtils.isEmpty(buyerVO.getBuyerId())?"":buyerVO.getBuyerId());
                cell1.setCellValue(StringUtils.isEmpty(buyerVO.getBuyerName())?"":buyerVO.getBuyerName());
                cell2.setCellValue(StringUtils.isEmpty(buyerVO.getBuyerShortName())?"":buyerVO.getBuyerShortName());
                cell3.setCellValue(StringUtils.isEmpty(buyerVO.getBuyerFullName())?"":buyerVO.getBuyerFullName());
                cell4.setCellValue(StringUtils.isEmpty(buyerVO.getBuyerSpell())?"":buyerVO.getBuyerSpell());
                cell5.setCellValue(StringUtils.isEmpty(buyerVO.getCityName())?"":buyerVO.getCityName());
                cell6.setCellValue(StringUtils.isEmpty(buyerVO.getBuyerTypeName())?"":buyerVO.getBuyerTypeName());
                cell7.setCellValue(StringUtils.isEmpty(buyerVO.getBuyerStateName())?"":buyerVO.getBuyerStateName());
                cell8.setCellValue(StringUtils.isEmpty(buyerVO.getBuyerContact())?"":buyerVO.getBuyerContact());
                cell9.setCellValue(StringUtils.isEmpty(buyerVO.getBuyerPhone())?"":buyerVO.getBuyerPhone());
                cell10.setCellValue(StringUtils.isEmpty(buyerVO.getBuyerMobile())?"":buyerVO.getBuyerMobile());
                cell9.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell10.setCellType(HSSFCell.CELL_TYPE_STRING);

                cell11.setCellValue(StringUtils.isEmpty(buyerVO.getBuyerEmail())?"":buyerVO.getBuyerEmail());
                cell12.setCellValue(StringUtils.isEmpty(buyerVO.getBuyerFax())?"":buyerVO.getBuyerFax());
                cell13.setCellValue(buyerVO.getBuyerJoinningFee()==null?"0.0000":buyerVO.getBuyerJoinningFee().toString());
                cell14.setCellValue(StringUtils.isEmpty(buyerVO.getBuyerStartDate())?"":DateUtil.date2String(buyerVO.getBuyerStartDate()));
                cell15.setCellValue(StringUtils.isEmpty(buyerVO.getBuyerEndDate())?"":DateUtil.date2String(buyerVO.getBuyerEndDate()));
                cell16.setCellValue(StringUtils.isEmpty(buyerVO.getLegalPerson())?"":buyerVO.getLegalPerson());
                cell17.setCellValue(StringUtils.isEmpty(buyerVO.getCompanyName())?"":buyerVO.getCompanyName());
                cell18.setCellValue(StringUtils.isEmpty(buyerVO.getBankName())?"":buyerVO.getBankName());
                cell19.setCellValue(StringUtils.isEmpty(buyerVO.getBankAccount())?"":buyerVO.getBankAccount());
                cell20.setCellValue(StringUtils.isEmpty(buyerVO.getTaxNumber())?"":buyerVO.getTaxNumber());
                cell21.setCellValue(StringUtils.isEmpty(buyerVO.getBuyerAddress())?"":buyerVO.getBuyerAddress());
                cell22.setCellValue(StringUtils.isEmpty(buyerVO.getBuyerDesc())?"":buyerVO.getBuyerDesc());
                cell23.setCellValue(StringUtils.isEmpty(buyerVO.getManageCenterName())?"":buyerVO.getManageCenterName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(out != null){
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return workbook;
    }



    //下载模板--产品
    public static Workbook writeProductExcel(List<Map<String,String>> mapList,List<Object> list){
        String tableName = list.get(0).toString();
        List<String> brandNameList = (List<String>)list.get(1);
        List<Map<String,String>> itemList = (List<Map<String,String>>)list.get(2);
        List<Map<String,Object>> productSubCategoryList = (List<Map<String,Object>>)list.get(3);
        List<String> uomList = (List<String>)list.get(4);

        Map<String,String> attrDefsMap = mapList.get(0);
        Map<String,String> attrValuesMap = mapList.get(1);
        Map<String,String> defTypeMap = mapList.get(2);

        int cloumnCount = attrDefsMap.size();
        Workbook workBook = new XSSFWorkbook();
        try {
            List<String> keyList = new ArrayList<>();
            List<String> attrValueList = new ArrayList<>();
            List<String> modelValueList = new ArrayList<>();
            List<String> defTypeList = new ArrayList<>();
            Sheet sheet = workBook.createSheet(tableName);

            Row firstRow = sheet.createRow(0);
            Row secondRow = sheet.createRow(1);
            Row thirdRow = sheet.createRow(2);
            Row fourthRow = sheet.createRow(3);

            String productNameModelVlue = "";
            String brandModelVlue = "";
            String categoryLevel1ModelVlue = "";
            String categoryLevel2ModelVlue = "";
            String uomModelVlue = "";
            switch (tableName){
                case "book" :
                    productNameModelVlue = "小鹿斑比";
                    brandModelVlue = "ICICLE";
                    categoryLevel1ModelVlue = "儿童区";
                    categoryLevel2ModelVlue = "儿童";
                    uomModelVlue = "套";
                    break;
                case "homehold" :
                    productNameModelVlue = "格斯多功能毯";
                    brandModelVlue = "家纺";
                    categoryLevel1ModelVlue = "多功能毯";
                    uomModelVlue = "件";
                    break;
                case "material" :
                    productNameModelVlue = "高级斑马纹颈带";
                    brandModelVlue = "物料";
                    categoryLevel1ModelVlue = "陈列配件";
                    categoryLevel2ModelVlue = "陈列配件";
                    uomModelVlue = "条";
                    break;
            }

            firstRow.createCell(0).setCellValue("商品码/款号");
            firstRow.createCell(1).setCellValue("商品名称");
            firstRow.createCell(2).setCellValue("品牌");
            firstRow.createCell(3).setCellValue("商品中类");
            secondRow.createCell(0).setCellValue("product_code");
            secondRow.createCell(1).setCellValue("product_name");
            secondRow.createCell(2).setCellValue("brand");
            secondRow.createCell(3).setCellValue("cate_zl");
            thirdRow.createCell(0).setCellValue("varchar");
            thirdRow.createCell(1).setCellValue("nvarchar");
            thirdRow.createCell(2).setCellValue("nvarchar");
            thirdRow.createCell(3).setCellValue("char");

            fourthRow.createCell(0).setCellValue("AAAAD333DD11A1");
            fourthRow.createCell(1).setCellValue(productNameModelVlue);
            fourthRow.createCell(2).setCellValue(brandModelVlue);
            fourthRow.createCell(3).setCellValue(categoryLevel1ModelVlue);

            if(tableName.equals("homehold")){
                firstRow.createCell(4).setCellValue("状态");
                firstRow.createCell(5).setCellValue("吊牌价");
                firstRow.createCell(6).setCellValue("标准成本");
                firstRow.createCell(7).setCellValue("单位");
                secondRow.createCell(4).setCellValue("status");
                secondRow.createCell(5).setCellValue("unit_price");
                secondRow.createCell(6).setCellValue("standard_cost");
                secondRow.createCell(7).setCellValue("uom");
                thirdRow.createCell(4).setCellValue("nvarchar");
                thirdRow.createCell(5).setCellValue("money");
                thirdRow.createCell(6).setCellValue("money");
                thirdRow.createCell(7).setCellValue("varchar");
                fourthRow.createCell(4).setCellValue("可用");
                fourthRow.createCell(5).setCellValue("50.000");
                fourthRow.createCell(6).setCellValue("50.6400");
                fourthRow.createCell(7).setCellValue(uomModelVlue);
            }else{
                firstRow.createCell(4).setCellValue("商品小类");
                firstRow.createCell(5).setCellValue("状态");
                firstRow.createCell(6).setCellValue("吊牌价");
                firstRow.createCell(7).setCellValue("标准成本");
                firstRow.createCell(8).setCellValue("单位");
                secondRow.createCell(4).setCellValue("cate_xl");
                secondRow.createCell(5).setCellValue("status");
                secondRow.createCell(6).setCellValue("unit_price");
                secondRow.createCell(7).setCellValue("standard_cost");
                secondRow.createCell(8).setCellValue("uom");
                thirdRow.createCell(4).setCellValue("char");
                thirdRow.createCell(5).setCellValue("nvarchar");
                thirdRow.createCell(6).setCellValue("money");
                thirdRow.createCell(7).setCellValue("money");
                thirdRow.createCell(8).setCellValue("varchar");
                fourthRow.createCell(4).setCellValue(categoryLevel2ModelVlue);
                fourthRow.createCell(5).setCellValue("可用");
                fourthRow.createCell(6).setCellValue("50.000");
                fourthRow.createCell(7).setCellValue("50.6400");
                fourthRow.createCell(8).setCellValue(uomModelVlue);

            }

            if(tableName.equals("material")){

                firstRow.createCell(9).setCellValue("女装线店正价");
                secondRow.createCell(9).setCellValue("nvzxdzj");
                thirdRow.createCell(9).setCellValue("varchar");
                fourthRow.createCell(9).setCellValue("✔");

                firstRow.createCell(10).setCellValue("男装线店正价");
                secondRow.createCell(10).setCellValue("nanzxdzj");
                thirdRow.createCell(10).setCellValue("varchar");
                fourthRow.createCell(10).setCellValue("✔");

                firstRow.createCell(11).setCellValue("巴黎线店正价");
                secondRow.createCell(11).setCellValue("blxdzj");
                thirdRow.createCell(11).setCellValue("varchar");
                fourthRow.createCell(11).setCellValue("✔");

                firstRow.createCell(12).setCellValue("工厂店正价");
                secondRow.createCell(12).setCellValue("gcdzj");
                thirdRow.createCell(12).setCellValue("varchar");
                fourthRow.createCell(12).setCellValue("✔");

                firstRow.createCell(13).setCellValue("卡纷店正价");
                secondRow.createCell(13).setCellValue("kfdzj");
                thirdRow.createCell(13).setCellValue("varchar");
                fourthRow.createCell(13).setCellValue("✔");

                firstRow.createCell(14).setCellValue("综合店正价");
                secondRow.createCell(14).setCellValue("zhdzj");
                thirdRow.createCell(14).setCellValue("varchar");
                fourthRow.createCell(14).setCellValue("✔");

                firstRow.createCell(15).setCellValue("电商店正价");
                secondRow.createCell(15).setCellValue("dsdzj");
                thirdRow.createCell(15).setCellValue("varchar");
                fourthRow.createCell(15).setCellValue("✔");

                firstRow.createCell(16).setCellValue("家居店正价");
                secondRow.createCell(16).setCellValue("jjdzj");
                thirdRow.createCell(16).setCellValue("varchar");
                fourthRow.createCell(16).setCellValue("✔");

                firstRow.createCell(17).setCellValue("品牌正价");
                secondRow.createCell(17).setCellValue("ppzj");
                thirdRow.createCell(17).setCellValue("varchar");
                fourthRow.createCell(17).setCellValue("✔");

                firstRow.createCell(18).setCellValue("卡纷工厂店正价");
                secondRow.createCell(18).setCellValue("kfgcdzj");
                thirdRow.createCell(18).setCellValue("varchar");
                fourthRow.createCell(18).setCellValue("✔");

                firstRow.createCell(19).setCellValue("女装线店折扣");
                secondRow.createCell(19).setCellValue("nvzxdzk");
                thirdRow.createCell(19).setCellValue("varchar");
                fourthRow.createCell(19).setCellValue("✔");

                firstRow.createCell(20).setCellValue("男装线店折扣");
                secondRow.createCell(20).setCellValue("nanzxdzk");
                thirdRow.createCell(20).setCellValue("varchar");
                fourthRow.createCell(20).setCellValue("✔");

                firstRow.createCell(21).setCellValue("巴黎线店折扣");
                secondRow.createCell(21).setCellValue("blxdzk");
                thirdRow.createCell(21).setCellValue("varchar");
                fourthRow.createCell(21).setCellValue("✔");

                firstRow.createCell(22).setCellValue("工厂店折扣");
                secondRow.createCell(22).setCellValue("gcdzk");
                thirdRow.createCell(22).setCellValue("varchar");
                fourthRow.createCell(22).setCellValue("✔");

                firstRow.createCell(23).setCellValue("卡纷店折扣");
                secondRow.createCell(23).setCellValue("kfdzk");
                thirdRow.createCell(23).setCellValue("varchar");
                fourthRow.createCell(23).setCellValue("✔");

                firstRow.createCell(24).setCellValue("综合店折扣");
                secondRow.createCell(24).setCellValue("zhdzk");
                thirdRow.createCell(24).setCellValue("varchar");
                fourthRow.createCell(24).setCellValue("✔");

                firstRow.createCell(25).setCellValue("电商店折扣");
                secondRow.createCell(25).setCellValue("dsdzk");
                thirdRow.createCell(25).setCellValue("varchar");
                fourthRow.createCell(25).setCellValue("✔");

                firstRow.createCell(26).setCellValue("家居店折扣");
                secondRow.createCell(26).setCellValue("jjdzk");
                thirdRow.createCell(26).setCellValue("varchar");
                fourthRow.createCell(26).setCellValue("✔");

                firstRow.createCell(27).setCellValue("品牌折扣");
                secondRow.createCell(27).setCellValue("ppzk");
                thirdRow.createCell(27).setCellValue("varchar");
                fourthRow.createCell(27).setCellValue("✔");

                firstRow.createCell(28).setCellValue("卡纷工厂店折扣");
                secondRow.createCell(28).setCellValue("kfgcdzk");
                thirdRow.createCell(28).setCellValue("varchar");
                fourthRow.createCell(28).setCellValue("✔");



            }
            attrDefsMap.forEach((k,v)->{
                keyList.add(k);
                attrValueList.add(v);
            });
            attrValuesMap.forEach((k,v)->{
                modelValueList.add(v);
            });
            defTypeMap.forEach((k,v)->{
                defTypeList.add(v);
            });

            Set<String> keySet = new HashSet<>();
            if(!ListUtil.isBlank(itemList)){
                for(Map<String,String> map : itemList){
                    keySet.add(map.get("def_code"));
                }
            }
            String categoryCode = tableName.equals("book")?CommonConstant.BOOKCATEGORYKEY:(tableName.equals("homehold")?CommonConstant.HOMEHOLDCATEGORYKEY:CommonConstant.MATERIALCATEGORYKEY);
            List<Map<String,Object>> categoryCodeFilterList = productSubCategoryList.stream().filter(a->a.get("category_code").toString().equals(categoryCode)).collect(Collectors.toList());

            String hiddenSheetName = "selectdata";
            Sheet hiddenSheet = workBook.createSheet(hiddenSheetName);
            workBook.setSheetHidden(workBook.getSheetIndex(hiddenSheet), true);

            int startRow = 3;
            int endRow = 1000;
            //下拉框选项列表--品牌
            String[] brandArray = brandNameList.toArray(new String[brandNameList.size()]) ;
            setDropdownList(brandArray,startRow,endRow,2,2,sheet);
            //下拉框选项列表--状态
            int statusIndex = tableName.equals("homehold")?4:5;
            int uomIndex = tableName.equals("homehold")?7:8;
            String[] statusArray = new String[] {"可用","不可用"};
            setDropdownList(statusArray,startRow,endRow,statusIndex,statusIndex,sheet);
            //下拉框选项列表--中类
            List<Map<String,Object>> categoryLevel1FilterList = categoryCodeFilterList.stream().filter(a->a.get("sub_category_level").toString().equals("1")).collect(Collectors.toList());
            if(!ListUtil.isBlank(categoryLevel1FilterList)){
                List<String> dropdownList = new ArrayList<>();
                for(Map<String,Object> map : categoryLevel1FilterList){
                    dropdownList.add(map.get("product_sub_category_name").toString());
                }
                if(!dropdownList.isEmpty()){
                    setDropdownList(dropdownList.toArray(new String[dropdownList.size()]),startRow,endRow,3,3,sheet);
                }
            }

            if(statusIndex==5){
                //下拉框选项列表--小类
                List<Map<String,Object>> categoryLevel2FilterList = categoryCodeFilterList.stream().filter(a->a.get("sub_category_level").toString().equals("2")).collect(Collectors.toList());
                if(!ListUtil.isBlank(categoryLevel2FilterList)){
                    List<String> dropdownList = new ArrayList<>();
                    for(Map<String,Object> map : categoryLevel2FilterList){
                        dropdownList.add(map.get("product_sub_category_name").toString());
                    }
                    if(!dropdownList.isEmpty()){
                        setDropdownList(dropdownList.toArray(new String[dropdownList.size()]),startRow,endRow,4,4,sheet);
                    }
                }
                setDropdownList(uomList.toArray(new String[uomList.size()]),startRow,endRow,4,4,sheet);
            }

            //单位
            setDropdownList(uomList.toArray(new String[uomList.size()]),startRow,endRow,uomIndex,uomIndex,sheet);

            int baseColumnCount = tableName.equals("material")?29:(tableName.equals("homehold")?8:9);
            for (int k = baseColumnCount; k <cloumnCount+baseColumnCount; k++) {
                Cell first = firstRow.createCell(k);
                first.setCellValue(attrValueList.get(k-baseColumnCount));

                Cell second = secondRow.createCell(k);
                String key = keyList.get(k-baseColumnCount);
                second.setCellValue(key);

                Cell third = thirdRow.createCell(k);
                third.setCellValue(defTypeList.get(k-baseColumnCount));

                Cell fourth = fourthRow.createCell(k);
                fourth.setCellValue(modelValueList.get(k-baseColumnCount));

                secondRow.setZeroHeight(true);
                thirdRow.setZeroHeight(true);

                if(keySet.contains(key)){
                    //填充item下拉框的值
                    List<Map<String,String>> itemFilterList = itemList.stream().filter(a->a.get("def_code").equals(key)).collect(Collectors.toList());
                    if(!ListUtil.isBlank(itemFilterList)){
                        List<String> dropdownList = new ArrayList<>();
                        for(Map<String,String> map : itemFilterList){
                            dropdownList.add(map.get("def_name"));
                        }
                        if(!dropdownList.isEmpty()){
                            setDropdownList(dropdownList.toArray(new String[dropdownList.size()]),startRow,endRow,k,k,sheet);
                        }
                    }
                }

                if(key.equals("category_level_third")){
                    List<Map<String,Object>> categoryLevel3FilterList = categoryCodeFilterList.stream().filter(a->a.get("sub_category_level").toString().equals("3")).collect(Collectors.toList());
                    if(!ListUtil.isBlank(categoryLevel3FilterList)){
                        List<String> thirdCategoryNameList = new ArrayList<>();
                        for(Map<String,Object> map : categoryLevel3FilterList){
                            thirdCategoryNameList.add(map.get("product_sub_category_name").toString());
                        }
                        setRefercenceList(hiddenSheet,sheet,hiddenSheetName,thirdCategoryNameList,startRow,endRow,k,k,0);
                    }
                }
            }

            if(tableName.equals("material")){
                //下拉框选项列表
                String[] array = new String[] {"✔","✘"};
                setDropdownList(array,startRow,endRow,9,28,sheet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workBook;
    }

    //下载模板--条码
    public static Workbook writeProductInfoExcel(List<Map<String,String>> batchList,List<Map<String,Object>> sizeList){
        Workbook workBook = new XSSFWorkbook();
        try {
            Sheet sheet = workBook.createSheet("productinfo");
            Row firstRow = sheet.createRow(0);
            Row secondRow = sheet.createRow(1);
            Row thirdRow = sheet.createRow(2);

            firstRow.createCell(0).setCellValue("商品码/款号");
            firstRow.createCell(1).setCellValue("SKU");
            firstRow.createCell(2).setCellValue("颜色");
            firstRow.createCell(3).setCellValue("批次");
            firstRow.createCell(4).setCellValue("尺码");
            firstRow.createCell(5).setCellValue("吊牌价");
            firstRow.createCell(6).setCellValue("标准成本");
            firstRow.createCell(7).setCellValue("销售成本");
            firstRow.createCell(8).setCellValue("安全码");
            firstRow.createCell(9).setCellValue("状态");
            Cell remarkCell0 = firstRow.createCell(10);
            remarkCell0.setCellValue("备注（导入时删除这一列）");

            secondRow.createCell(0).setCellValue("BBB0018C21911A");
            secondRow.createCell(1).setCellValue("BBB0018C21911A06");
            secondRow.createCell(2).setCellValue("亚麻色");
            secondRow.createCell(3).setCellValue("第一批");
            secondRow.createCell(4).setCellValue("06");
            secondRow.createCell(5).setCellValue("1796");
            secondRow.createCell(6).setCellValue("495.44");
            secondRow.createCell(7).setCellValue("495.44");
            secondRow.createCell(8).setCellValue("P218B4");
            secondRow.createCell(9).setCellValue("有效");
            Cell remarkCell1 = secondRow.createCell(10);
            remarkCell1.setCellValue("20SS之前的商品按照这个模板");

            thirdRow.createCell(0).setCellValue("AAA0719B00011A02");
            thirdRow.createCell(1).setCellValue("AAA0719B00011A02");
            thirdRow.createCell(2).setCellValue("亚麻色");
            thirdRow.createCell(3).setCellValue("第一批");
            thirdRow.createCell(4).setCellValue("FF");
            thirdRow.createCell(5).setCellValue("1796");
            thirdRow.createCell(6).setCellValue("495.44");
            thirdRow.createCell(7).setCellValue("495.44");
            thirdRow.createCell(8).setCellValue("P218B4");
            thirdRow.createCell(9).setCellValue("有效");
            Cell remarkCell2 = thirdRow.createCell(10);
            remarkCell2.setCellValue("07/08线按照这个模板，款号和SKU一致");

            CellStyle yellowCellstyle = workBook.createCellStyle();
            yellowCellstyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
            yellowCellstyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
            remarkCell0.setCellStyle(yellowCellstyle);
            remarkCell1.setCellStyle(yellowCellstyle);
            remarkCell2.setCellStyle(yellowCellstyle);

            List<String> batchKeyList = new ArrayList<>();
            List<String> batchValueList = new ArrayList<>();
            List<String> sizeKeyList = new ArrayList<>();
            List<String> sizeValueList = new ArrayList<>();
            if(!ListUtil.isBlank(batchList)){
                for (Map<String,String> map : batchList){
                    batchKeyList.add(map.get("item_key"));
                    batchValueList.add(map.get("item_value"));
                }
            }
            if(!ListUtil.isBlank(sizeList)){
                for (Map<String,Object> map : sizeList){
                    sizeKeyList.add(map.get("size_code").toString());
                    sizeValueList.add(map.get("size_desc").toString());
                }
            }
            String hiddenSheetName = "selectdata";
            Sheet hiddenSheet = workBook.createSheet(hiddenSheetName);
            workBook.setSheetHidden(workBook.getSheetIndex(hiddenSheet), true);
            int startRow = 1;
            int endRow = 1000;
            //批次
            setDropdownList(batchValueList.toArray(new String[batchValueList.size()]),startRow,endRow,3,3,sheet);
            //状态
            List<String> statusList = Arrays.asList("有效","无效");
            setDropdownList(statusList.toArray(new String[statusList.size()]),startRow,endRow,9,9,sheet);
            //尺码
            setRefercenceList(hiddenSheet,sheet,hiddenSheetName,sizeKeyList,startRow,endRow,4,4,0);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return workBook;
    }

    private static void setDropdownList(String [] array,int startRow,int endRow,int startColumn,int endColumn,Sheet sheet){
        XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper((XSSFSheet) sheet);
        XSSFDataValidationConstraint dvConstraint = (XSSFDataValidationConstraint) dvHelper.createExplicitListConstraint(array);
        CellRangeAddressList addressList = null;
        XSSFDataValidation validation = null;
        //添加下拉框
        for (int i = startRow; i < endRow; i++) {
            addressList = new CellRangeAddressList(i, i, startColumn, endColumn);
            validation = (XSSFDataValidation) dvHelper.createValidation(dvConstraint, addressList);
            validation.setSuppressDropDownArrow(true);
            validation.setSuppressDropDownArrow(true);
            validation.setShowErrorBox(true);
            sheet.addValidationData(validation);
        }
    }

    private static void setRefercenceList(Sheet hiddenSheet,Sheet sheet,String hiddenSheetName,List<String> list,int startRow,int endRow,int startColumn,int endColumn,int columnNum){
        if(!ListUtil.isBlank(list)){
            for(int i = 0; i< list.size();i++){
                Row row = hiddenSheet.createRow(i);
                Cell hiddenCell = row.createCell(columnNum);
                hiddenCell.setCellValue(list.get(i));
            }
            String startLetter = StringUtil.num2Letter(columnNum+1);
            String strFormula = hiddenSheetName +  "!"+startLetter+"$1:"+startLetter+"$" + list.size();
            XSSFDataValidationConstraint constraint = new XSSFDataValidationConstraint(DataValidationConstraint.ValidationType.LIST,strFormula);
            CellRangeAddressList regions = new CellRangeAddressList(startRow,endRow, startColumn, endColumn);
            DataValidationHelper help = new XSSFDataValidationHelper((XSSFSheet) sheet);
            DataValidation validation = help.createValidation(constraint, regions);
            sheet.addValidationData(validation);
        }
    }

    public static List<Map<String,String>> getTitleMapList(){
        List<Map<String,String>> mapList = new ArrayList<>();
        Map<String,String> map1 = new HashMap<>();
        map1.put("data","product_code");
        map1.put("title","产品编码");
        Map<String,String> map2 = new HashMap<>();
        map2.put("data","product_name");
        map2.put("title","商品名称");
        Map<String,String> map3 = new HashMap<>();
        map3.put("data","unit_price");
        map3.put("title","价格");
        Map<String,String> map4 = new HashMap<>();
        map4.put("data","uom");
        map4.put("title","单位");
        Map<String,String> map5 = new HashMap<>();
        map5.put("data","standard_cost");
        map5.put("title","标准成本");
        Map<String,String> map6 = new HashMap<>();
        map6.put("data","cate_zl");
        map6.put("title","商品中类");
        Map<String,String> map7 = new HashMap<>();
        map7.put("data","cate_xl");
        map7.put("title","商品小类");
        Map<String,String> map8 = new HashMap<>();
        map8.put("data","product_desc");
        map8.put("title","商品描述");
        Map<String,String> map9 = new HashMap<>();
        map9.put("data","brand");
        map9.put("title","品牌");
        Map<String,String> map10 = new HashMap<>();
        map10.put("data","status");
        map10.put("title","状态");

        mapList.add(map1);
        mapList.add(map2);
        mapList.add(map3);
        mapList.add(map4);
        mapList.add(map5);
        mapList.add(map6);
        mapList.add(map7);
        mapList.add(map8);
        mapList.add(map9);
        mapList.add(map10);
        return mapList;
    }


    public static List<String> getBaseKeyList(){
        List<String> codeList = new ArrayList<>();;
        codeList.add("product_code");
        codeList.add("unit_price");
        codeList.add("product_name");
        codeList.add("product_desc");
        codeList.add("uom");
        codeList.add("cate_zl");
        codeList.add("cate_xl");
        codeList.add("standard_cost");
        codeList.add("brand");
        codeList.add("status");
        codeList.add("product_line");
        return codeList;
    }


    public static List<Map<String,String>> getCostumeCodeMapList(){
        List<Map<String,String>> mapList = new ArrayList<>();
        Map<String,String> map1 = new HashMap<>();
        map1.put("title","产品编码");
        map1.put("data","productCode");
        mapList.add(map1);
        Map<String,String> map2 = new HashMap<>();
        map2.put("title","产品名称");
        map2.put("data","productName");
        mapList.add(map2);
        Map<String,String> map3 = new HashMap<>();
        map3.put("title","单位");
        map3.put("data","uom");
        mapList.add(map3);
        Map<String,String> map4 = new HashMap<>();
        map4.put("title","价格");
        map4.put("data","unitPrice");
        mapList.add(map4);
        Map<String,String> map5 = new HashMap<>();
        map5.put("title","成本");
        map5.put("data","standardCost");
        mapList.add(map5);
        Map<String,String> map6 = new HashMap<>();
        map6.put("title","大类");
        map6.put("data","cateDl");
        mapList.add(map6);
        Map<String,String> map7 = new HashMap<>();
        map7.put("title","中类");
        map7.put("data","cateZl");
        mapList.add(map7);
        Map<String,String> map8 = new HashMap<>();
        map8.put("title","小类");
        map8.put("data","cateXl");
        mapList.add(map8);
        Map<String,String> map9 = new HashMap<>();
        map9.put("title","产品描述");
        map9.put("data","productDesc");
        mapList.add(map9);
        Map<String,String> map10 = new HashMap<>();
        map10.put("title","品牌");
        map10.put("data","brand");
        mapList.add(map10);
        Map<String,String> map11 = new HashMap<>();
        map11.put("title","安全码");
        map11.put("data","securityCode");
        mapList.add(map11);
        Map<String,String> map12 = new HashMap<>();
        map12.put("title","预估倍率");
        map12.put("data","estimatedRate");
        mapList.add(map12);
        Map<String,String> map13 = new HashMap<>();
        map13.put("title","订单序号");
        map13.put("data","orderNo");
        mapList.add(map13);
        Map<String,String> map14 = new HashMap<>();
        map14.put("title","开发号");
        map14.put("data","devNo");
        mapList.add(map14);
        Map<String,String> map15 = new HashMap<>();
        map15.put("title","款型号");
        map15.put("data","styleNo");
        mapList.add(map15);
        Map<String,String> map16 = new HashMap<>();
        map16.put("title","样板号");
        map16.put("data","modelNo");
        mapList.add(map16);
        Map<String,String> map17 = new HashMap<>();
        map17.put("title","线");
        map17.put("data","line");
        mapList.add(map17);
        Map<String,String> map18 = new HashMap<>();
        map18.put("title","面料编号");
        map18.put("data","materialNo");
        mapList.add(map18);
        Map<String,String> map19 = new HashMap<>();
        map19.put("title","面料名称");
        map19.put("data","materialName");
        mapList.add(map19);
        Map<String,String> map20 = new HashMap<>();
        map20.put("title","颜色");
        map20.put("data","colorName");
        mapList.add(map20);
        Map<String,String> map21 = new HashMap<>();
        map21.put("title","品种");
        map21.put("data","productClass");
        mapList.add(map21);
        Map<String,String> map22 = new HashMap<>();
        map22.put("title","年");
        map22.put("data","year");
        mapList.add(map22);
        Map<String,String> map23 = new HashMap<>();
        map23.put("title","季节");
        map23.put("data","natureSeason");
        mapList.add(map23);
        Map<String,String> map24 = new HashMap<>();
        map24.put("title","开发季");
        map24.put("data","devSeason");
        mapList.add(map24);
        Map<String,String> map25 = new HashMap<>();
        map25.put("title","波");
        map25.put("data","wave");
        mapList.add(map25);
        Map<String,String> map26 = new HashMap<>();
        map26.put("title","段");
        map26.put("data","band");
        mapList.add(map26);
        Map<String,String> map27 = new HashMap<>();
        map27.put("title","组");
        map27.put("data","icicleGroup");
        mapList.add(map27);
        Map<String,String> map28 = new HashMap<>();
        map28.put("title","上市日期");
        map28.put("data","salesDate");
        mapList.add(map28);
        Map<String,String> map29 = new HashMap<>();
        map29.put("title","色卡号");
        map29.put("data","colorCardNo");
        mapList.add(map29);
        Map<String,String> map30 = new HashMap<>();
        map30.put("title","色卡名");
        map30.put("data","colorCardName");
        mapList.add(map30);
        Map<String,String> map31 = new HashMap<>();
        map31.put("title","色系");
        map31.put("data","colourSystem");
        mapList.add(map31);
        Map<String,String> map32 = new HashMap<>();
        map32.put("title","工作组");
        map32.put("data","workGroup");
        mapList.add(map32);
        Map<String,String> map33 = new HashMap<>();
        map33.put("title","尺寸组");
        map33.put("data","sizeGroup");
        mapList.add(map33);
        Map<String,String> map34 = new HashMap<>();
        map34.put("title","统一编码");
        map34.put("data","code");
        mapList.add(map34);
        Map<String,String> map35 = new HashMap<>();
        map35.put("title","规格");
        map35.put("data","standard");
        mapList.add(map35);
        Map<String,String> map36 = new HashMap<>();
        map36.put("title","批次");
        map36.put("data","batch");
        mapList.add(map36);
        Map<String,String> map37 = new HashMap<>();
        map37.put("title","款式规则");
        map37.put("data","styleRule");
        mapList.add(map37);
        Map<String,String> map38 = new HashMap<>();
        map38.put("title","供应商");
        map38.put("data","supplier");
        mapList.add(map38);
        Map<String,String> map39 = new HashMap<>();
        map39.put("title","建档人");
        map39.put("data","opr");
        mapList.add(map39);
        Map<String,String> map40 = new HashMap<>();
        map40.put("title","建档日期");
        map40.put("data","opDate");
        mapList.add(map40);
        Map<String,String> map41 = new HashMap<>();
        map41.put("title","状态");
        map41.put("data","syncStatus");
        mapList.add(map41);
        Map<String,String> map42 = new HashMap<>();
        map42.put("productName","服饰");
        mapList.add(map42);
        return mapList;

    }

    public static List<String> getExtendAttrBaseList(){
        List<String> extendAttrBaseList = new ArrayList<>();
        extendAttrBaseList.add("last_updated_by");
        extendAttrBaseList.add("product_id");
        extendAttrBaseList.add("creation_date");
        extendAttrBaseList.add("product_code");
        extendAttrBaseList.add("created_by");
        extendAttrBaseList.add("last_update_date");
        return extendAttrBaseList;
    }

    public static List<String> getYearList(){
        return Arrays.asList("2017年","2018年","2019年","2020年","2021年","2022年","2023年","2024年","2025年","2026年");
    }
    public static List<String> getStoreTypeClassKeyList(){
        return Arrays.asList("ppzj","kfgcdzj","ppzk","kfgcdzk","gcdzj","nanzxdzj","blxdzj","nvzxdzj","jjdzj","kfdzj","dsdzj","zhdzj", "gcdzk","nanzxdzk","blxdzk","nvzxdzk","jjdzk","kfdzk","dsdzk","zhdzk");
    }

    public static void downloadExcelWithStream(HttpServletResponse response, Workbook workbook, String tableName){
        try {
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            workbook.write(os);
            byte[] content = os.toByteArray();
            InputStream is = new ByteArrayInputStream(content);
            response.reset();
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(tableName + ".xlsx","utf-8"));
            ServletOutputStream out = response.getOutputStream();
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            while ((-1 != (bytesRead = bis.read(buff, 0, buff.length)))) {
                bos.write(buff, 0, bytesRead);
            }
            bis.close();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
