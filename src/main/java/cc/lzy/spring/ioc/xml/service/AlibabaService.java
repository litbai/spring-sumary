/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package cc.lzy.spring.ioc.xml.service;

/**
 * Alibaba Service  构造复杂
 *
 * @author taigai
 * @version AlibabaService.java, v 0.1 2021年03月27日 18:48 taigai Exp $
 */
public class AlibabaService {

    private String taobao;
    private String tmall;
    private String antGroup;
    private String aliExpress;
    private String kaola;
    private String cainiao;
    private String youku;
    private String gaode;

    public AlibabaService(String taobao, String tmall, String antGroup, String aliExpress, String kaola, String cainiao, String youku, String gaode) {
        System.out.println("AlibabaService constructor invoked..");
        this.taobao = taobao;
        this.tmall = tmall;
        this.antGroup = antGroup;
        this.aliExpress = aliExpress;
        this.kaola = kaola;
        this.cainiao = cainiao;
        this.youku = youku;
        this.gaode = gaode;
    }

    public void init() {
        System.out.println(this.getClass() + " init...s");
    }

    @Override
    public String toString() {
        return "AlibabaService{" + "taobao='" + taobao + '\'' + ", tmall='" + tmall + '\'' + ", antGroup='" + antGroup + '\'' + ", aliExpress='" + aliExpress + '\'' + ", kaola='" + kaola + '\'' + ", cainiao='" + cainiao + '\'' + ", youku='" + youku + '\'' + ", gaode='" + gaode + '\'' + '}';
    }

    /**
     * Getter method for property <tt>taobao</tt>.
     *
     * @return property value of taobao
     */
    public String getTaobao() {
        return taobao;
    }

    /**
     * Setter method for property <tt>taobao</tt>.
     *
     * @param taobao value to be assigned to property taobao
     */
    public void setTaobao(String taobao) {
        this.taobao = taobao;
    }

    /**
     * Getter method for property <tt>tmall</tt>.
     *
     * @return property value of tmall
     */
    public String getTmall() {
        return tmall;
    }

    /**
     * Setter method for property <tt>tmall</tt>.
     *
     * @param tmall value to be assigned to property tmall
     */
    public void setTmall(String tmall) {
        this.tmall = tmall;
    }

    /**
     * Getter method for property <tt>antGroup</tt>.
     *
     * @return property value of antGroup
     */
    public String getAntGroup() {
        return antGroup;
    }

    /**
     * Setter method for property <tt>antGroup</tt>.
     *
     * @param antGroup value to be assigned to property antGroup
     */
    public void setAntGroup(String antGroup) {
        this.antGroup = antGroup;
    }

    /**
     * Getter method for property <tt>aliExpress</tt>.
     *
     * @return property value of aliExpress
     */
    public String getAliExpress() {
        return aliExpress;
    }

    /**
     * Setter method for property <tt>aliExpress</tt>.
     *
     * @param aliExpress value to be assigned to property aliExpress
     */
    public void setAliExpress(String aliExpress) {
        this.aliExpress = aliExpress;
    }

    /**
     * Getter method for property <tt>kaola</tt>.
     *
     * @return property value of kaola
     */
    public String getKaola() {
        return kaola;
    }

    /**
     * Setter method for property <tt>kaola</tt>.
     *
     * @param kaola value to be assigned to property kaola
     */
    public void setKaola(String kaola) {
        this.kaola = kaola;
    }

    /**
     * Getter method for property <tt>cainiao</tt>.
     *
     * @return property value of cainiao
     */
    public String getCainiao() {
        return cainiao;
    }

    /**
     * Setter method for property <tt>cainiao</tt>.
     *
     * @param cainiao value to be assigned to property cainiao
     */
    public void setCainiao(String cainiao) {
        this.cainiao = cainiao;
    }

    /**
     * Getter method for property <tt>youku</tt>.
     *
     * @return property value of youku
     */
    public String getYouku() {
        return youku;
    }

    /**
     * Setter method for property <tt>youku</tt>.
     *
     * @param youku value to be assigned to property youku
     */
    public void setYouku(String youku) {
        this.youku = youku;
    }

    /**
     * Getter method for property <tt>gaode</tt>.
     *
     * @return property value of gaode
     */
    public String getGaode() {
        return gaode;
    }

    /**
     * Setter method for property <tt>gaode</tt>.
     *
     * @param gaode value to be assigned to property gaode
     */
    public void setGaode(String gaode) {
        this.gaode = gaode;
    }
}