package xyd.com.bydshop.version;

/**
 * @author: zhaoxiaolei
 * @date: 2017/5/8
 * @time: 14:30
 * @description:  版本更新
 */

public class VersionUpdateModel {
    private int build;
    private boolean needUpgrade;
    private String upgradeUrl;
    private boolean mustUpgrade;
    private String description;
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public boolean isMustUpgrade() {
        return mustUpgrade;
    }

    public void setMustUpgrade(boolean mustUpgrade) {
        this.mustUpgrade = mustUpgrade;
    }


    public int getBuild() {
        return build;
    }

    public void setBuild(int build) {
        this.build = build;
    }

    public boolean isNeedUpgrade() {
        return needUpgrade;
    }

    public void setNeedUpgrade(boolean needUpgrade) {
        this.needUpgrade = needUpgrade;
    }


    public String getUpgradeUrl() {
        return upgradeUrl;
    }

    public void setUpgradeUrl(String upgradeUrl) {
        this.upgradeUrl = upgradeUrl;
    }



}
