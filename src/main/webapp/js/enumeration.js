/**
 * HTML静态枚举
 *
 * @author tangyuhan 349910
 * @version v1.0
 * Date:2016/12/8 20:21 下午
 */
var MEnum = (function() {

    //核销类型
    var verifyType = {"BADDEBTS":"坏账损失","BR":"坏账冲应收","CP":"结转冲应付","EP":"异常货应收冲应付","ER":"异常货应付冲应收","INCOME":"保险理赔","PP":"付款冲应付","PTR":"应付转余额","RER":"应收冲应收","RP":"应收冲应付","RR":"收款冲应收"};
    //流水子类型
    var orderSubType = {"A":"航空公司运费应付","AA":"空运到达代理应收","AAC":"空运代理代收货款应收","AAD":"空运到达代理应付","AAO":"空运出发代理应付","AD":"事后折应付","AO":"空运其他应付","APC":"代收货款应付","AR":"空运其他应收","C":"理赔应付","CCTYF":"仓储调整应付","CCTYS":"仓储调整应收","CCWYF":"仓储外发调整应付","COR":"结转应收","CP":"服务补救应付","CR":"代收货款应收","DL":"到达快递代理应收","DLC":"快递代理代收货款应收","DP":"偏线代理应收","DR":"到达应收","EP":"异常货应付","EPC":"委托派费转收款","ER":"异常货应收","HHDYF":"合伙人代收货款手续费应付","HIP":"家装应付","HIR":"家装应收","HZD":"坏账应收","HZP":"坏账应付","LD":"快递代理外发应付","LDO":"快递代理其他应付","LR":"快递代理其他应收","LRF":"快递服务补救应付","OR":"始发应收","PB":"合伙人奖励应付","PDDF":"合伙人委托派费应付","PDFP":"合伙人到达提成应付","PDFR":"合伙人委托派费应收","PER":"合伙人差错应收","PFCP":"合伙人到付运费应付","PFCR":"合伙人到付运费应收","PL":"偏线外发应付","PLC":"偏线代理代收货款应收","PLE":"合伙人快递差错应付","PO":"偏线其他应付","POR":"合伙人始发提成应收","PP":"合伙人罚款应收","PR":"偏线其他应收","PTF":"合伙人培训会务应收","QTYF":"其他应付","RC":"临时租车应付","RR":"小票应收","SF":"装卸费应付","TF1":"外请车（首款）应付","TF2":"整车（首款）应付","TL1":"外请车（尾款）应付","TL2":"整车（尾款）应付","TPFC":"到付转收款","WBCS":"仓储补充费用应收","WCKS":"仓储仓库应收","WD":"仓储配送应收","WDTF":"仓储配送调整应付","WDTS":"仓储配送调整应收","WFYF":"外发应付","WHO":"仓储外发应付","WKF":"仓储KPI应付","WKNS":"仓储库内操作应收","WKS":"仓储KPI应收","WOF":"仓储其他应付","WOHF":"仓储外发其他应付","WOHS":"仓储外发调整应收","WOOS":"仓储外发其他应收","WOP":"包装其他应付","WOR":"包装其他应收","WOS":"仓储其他应收","WP":"包装应付","WQYF":"外请车应付","WS":"仓储存储应收","WSS":"仓储补充应收","WST":"仓储补充调整应付","WSTF":"仓储存储调整应付","WSTS":"仓储存储调整应收","WW":"仓储库内应收","WWTF":"仓储库内调整应付","WWTS":"仓储库内调整应收","WXTS":"仓储系统使用费应收","WZZS":"增值服务应收","ZCYF":"整车应付","ZKYF":"折扣单应付"};
    //支付类型
    var payStatusType = {"CREATE":"未支付","DONE":"已支付","PAYING":"支付中"};
    //单据类型
    var assOperationOutBillType =  {"CASHPAYMENT":"现金缴款单","COLLECTIONBILL":"收款单","PAYMENTBILL":"付款单","TRADERBILL":"物流交易单","VERIFYBILL":"核销单"};
    //产品类型
    var productType= {"AF":"精准空运","BGFLF":"精准大票卡航","BGFSF":"精准大票城运","BGLRF":"精准大票汽运(长)","BGSRF":"精准大票汽运(短)","CAR":"整车","DEAP":"特准快件","DTD":"精准大票.经济件","EPEP":"电商尊享","EXPR":"快递","FIXED":"一口价产品","FLF":"精准卡航","FSF":"精准城运","GTEC":"快递报关通-快","GTSE":"快递报关通-标","ICEC":"国际快递-快","ICES":"国际快递-标","ICSE":"同城当日达","LRF":"精准汽运(长途)","MENU":"菜单式产品","PACKAGE":"标准快递","PCP":"精准包裹","PLF":"汽运偏线","PROXY":"代运营产品","RCP":"3.60特惠件","SHIP":"快运","SRF":"精准汽运(短途)","STANDARD":"标准产品","TZKJ":"特准快件","WVH":"整车(三级)","YTY":"精准大票.标准件"};
    //收入类别
    var incomeCategories= {"A":"事故赔款","AC":"异常代收货款","AD":"加收派送费","B":"客户多付运费","BF":"品牌使用费 ","DF":"接货费","DR":"押金回收","DU":"送货上楼费","EC":"外部赔款","F":"放空费","FC":"富余仓库转租收入","FO":"叉车费","G":"盘点长款金额","H":"收银员卡利息","HC":"银票手续费 ","O":"租房违约金","P":"包装费","PD":"自提改派送","R":"卖废品","RB":"还借支","RF":"备用金上缴","RFC":"更改费","SB":"网银盾返款 ","SF":"系统使用费","W":"保管费","WE":"进仓费"};
    //打款方式
    var paymentType = {"BE":"余额","CD":"银行卡","CH":"现金","CT":"月结","DT":"临时欠款","FC":"到付","FCUS":"到付转预收","NT":"支票","OL":"网上支付","PO":"网上支付，电汇，支票，银行卡，都是使用打款编码结清","TT":"电汇"};
    //来源单据类型
    var sourceType = {"AP":"合大票清单","AW":"航空正单","HZ":"坏账","PL":"外发单","R":"小票","RC":"临时租车","RGLR":"人工录入","RS":"汽运配置单","TP":"中转提货清单","W":"运单","WI":"入库单","WO":"出库单","WS":"库内单"};
    //关联类型
    var relatedType = {"STATEMENT":"关联类型-对账单","TABLE":"关联类型-关联关系表","TRADE":"关联类型-物流交易"};
    //提货方式
    var pickUpType = {"AIRPORT_PICKUP":"空运机场自提","AIR_PICKUP_FREE":"汽运自提","DELIVER":"汽运送货进仓","DELIVER_AIR":"空运免费送货","DELIVER_FLOOR":"大件上楼费","DELIVER_INGA_AIR":"空运送货进仓","DELIVER_INNER_PICKUP":"送货上楼安装（家居）","DELIVER_NOUP":"汽运送货(不含上楼)","DELIVER_NOUP_AIR":"送货(不含上楼)","DELIVER_STORAGE":"汽运送货（上楼）","DELIVER_UP":"汽运内部自提","DELIVER_UP_AIR":"空运送货上楼","INNER_PICKUP":"内部带货送货","LARGE_DELIVER_UP_AIR":"大件上楼费","PICKUP_TO_DOOR":"送货上门","RYFJ":"燃油附加费","SELF_PICKUP":"自提","SELF_PICKUP_AIR":"空运自提(不含机场提货费)","SELF_PICKUP_FREE_AIR":"空运免费自提","ZHXX":"综合服务费"};
    var result={};
    result.OrderSubType=function(){
        return orderSubType;
    };
    result.VerifyType=function(){
        return verifyType;
    };
    result.PayStatusType=function(){
        return payStatusType;
    };
    result.AssOperationOutBillType=function(){
        return assOperationOutBillType;
    };
    result.ProductType=function(){
        return productType;
    };
    result.IncomeCategories=function(){
        return incomeCategories;
    };
    result.PaymentType=function(){
        return paymentType;
    };
    result.SourceType=function(){
        return sourceType;
    };
    result.RelatedType=function(){
        return relatedType;
    };
    result.PickUpType=function(){
        return pickUpType;
    };
    return result;
})();
