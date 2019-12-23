package com.icicle.masterdb.pojo.vo;

import com.icicle.masterdb.model.ColorCard;
import com.icicle.masterdb.util.StringUtil;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;

/**
 * @author CodeGeneratorUtil
 */
public class ColorCardVO {
    List<ColorCard> colorCards;

    private String colorCardCode;

    private String colorCardName;

    private String status;

    private Date createdDate;

    private Date lastUpdateDate;

    private String language;


    public List<ColorCard> getColorCards() {
        return colorCards;
    }

    public void setColorCards(List<ColorCard> colorCards) {
        this.colorCards = colorCards;
    }

    public String getColorCardCode() {
        return colorCardCode;
    }

    public void setColorCardCode(String colorCardCode) {
        this.colorCardCode = colorCardCode;
    }

    public String getColorCardName() {
        return colorCardName;
    }

    public void setColorCardName(String colorCardName) {
        this.colorCardName = colorCardName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return StringUtil.toSimpleString(this);
    }
}
