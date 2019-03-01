package ru.avalon.java.dev.ocpjp.labs.models;

/**
 *
 * @author Ella
 */
public class CommodityImpl implements Commodity {

    private String code;
    private String vendorCode;
    private String name;
    private double price;
    private int residue;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getVendorCode() {
        return vendorCode;
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

    static class CommodityBuilderImpl implements CommodityBuilder {

        private CommodityImpl commodity = new CommodityImpl();

        CommodityBuilderImpl() {
        }

        @Override
        public CommodityBuilder code(String code) {
            commodity.code = code;
            return this;
        }

        @Override
        public CommodityBuilder vendorCode(String vendorCode) {
            commodity.vendorCode = vendorCode;
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
            Commodity result = commodity;
            commodity = new CommodityImpl();
            return result;
        }

    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(code).append(" ");
        sb.append(vendorCode).append(" ");
        sb.append(name).append(" ");
        sb.append(price);
        return sb.toString();
    }
}
