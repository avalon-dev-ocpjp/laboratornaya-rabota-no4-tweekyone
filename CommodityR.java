/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.dev.ocpjp.labs.models;

/**
 *
 * @author MultatulyIM
 */
public class CommodityR implements Commodity {

    private String code;
    private String vendorcode;
    private String name;
    private double price;
    private int residue;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getVendorCode() {
        return vendorcode;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public int getResidue() {
        return residue;
    }

    static class CommodityBuilderR implements CommodityBuilder {

        private CommodityR commodity = new CommodityR();

        CommodityBuilderR() {
        }

        @Override
        public CommodityBuilder code(String code) {
            commodity.code = code;
            return this;
        }

        @Override
        public CommodityBuilder vendorCode(String vendorCode) {
            commodity.vendorcode = vendorCode;
            return this;
        }

        @Override
        public CommodityBuilder name(String name) {
            commodity.name = name;
            return this;
        }

        @Override
        public CommodityBuilder price(double price) {
            commodity.price = price;
            return this;
        }

        @Override
        public CommodityBuilder residue(int residue) {
            commodity.residue = residue;
            return this;
        }

        @Override
        public Commodity build() {
            Commodity res = commodity;
            commodity = new CommodityR();
            return res;
        }

    }


    @Override
//    public String getString(){
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(code).append(";").append(vendorcode).append(";").append(name)
                .append(';').append(price);
        return str.toString();
    }
}
