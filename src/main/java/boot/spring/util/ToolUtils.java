package boot.spring.util;

import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ToolUtils {

    /**
     * from:yyyy-MM-dd HH:mm:ss
     * to: "yyyy-MM-dd'T'HH:mm:ss'Z'"
     * 2013-01-30T07:00:00Z
     */
    public static String dataFormate(String time) {
        String data = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date data1 = sdf.parse(time);
            data = simpleDateFormat.format(data1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * 2013-01-30T07:00:00Z
     */
    public static String EsDateTimeTransfrom(String time, String fromPattern, String toPattern) {
        String data = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(toPattern);
        SimpleDateFormat sdf = new SimpleDateFormat(fromPattern);
        try {
            Date data1 = sdf.parse(time);
            data = simpleDateFormat.format(data1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * 处理关键字
     * @param keywords
     */
    public static String handKeyword(String keywords) {
        String handleKeyWords = "";
        if (StringUtils.isNotBlank(keywords)) {
            String[] keywordArray = keywords.split(" ");
            if (keywordArray.length != 0) {
                if (keywordArray.length != 1) {
                    for (int i = 0; i < keywordArray.length; i++) {
                        if (i == keywordArray.length - 1) {
                            if (!keywordArray[i].equalsIgnoreCase("AND") && !keywordArray[i].equalsIgnoreCase("OR") && !keywordArray[i].equalsIgnoreCase("OR")) {
                                if (keywordArray[i].startsWith("(")) {
                                    if (keywordArray[i].contains("NOT") || keywordArray[i].contains("not")) {
                                        handleKeyWords += keywordArray[i].toUpperCase();
                                        continue;
                                    }
                                    int frontIndex = keywordArray[i].lastIndexOf("(");
                                    handleKeyWords += keywordArray[i].substring(0, frontIndex + 1) + "\"" + keywordArray[i].substring(frontIndex + 1) + "\"";
                                } else if (keywordArray[i].endsWith(")")) {
                                    int backIndex = keywordArray[i].indexOf(")");
                                    handleKeyWords += "\"" + keywordArray[i].substring(0, backIndex) + "\"" + keywordArray[i].substring(backIndex);
                                } else {
                                    handleKeyWords += "\"" + keywordArray[i].toUpperCase() + "\"";
                                }
                            } else {
                                handleKeyWords += keywordArray[i];
                            }
                        } else {
                            if (!keywordArray[i].equalsIgnoreCase("AND") && !keywordArray[i].equalsIgnoreCase("OR") && !keywordArray[i].equalsIgnoreCase("NOT")) {
                                if (keywordArray[i].startsWith("(")) {
                                    if (keywordArray[i].contains("NOT") || keywordArray[i].contains("not")) {
                                        handleKeyWords += keywordArray[i].toUpperCase() + " ";
                                        continue;
                                    }
                                    int frontIndex = keywordArray[i].lastIndexOf("(");
                                    handleKeyWords += keywordArray[i].substring(0, frontIndex + 1) + "\"" + keywordArray[i].substring(frontIndex + 1) + "\" ";
                                } else if (keywordArray[i].endsWith(")")) {
                                    int backIndex = keywordArray[i].indexOf(")");
                                    handleKeyWords += "\"" + keywordArray[i].substring(0, backIndex) + "\"" + keywordArray[i].substring(backIndex) + " ";
                                } else {
                                    handleKeyWords += "\"" + keywordArray[i] + "\" ";
                                }
                            } else {
                                handleKeyWords += keywordArray[i].toUpperCase() + " ";
                            }
                        }
                    }
                } else {
                    handleKeyWords += "\"" + keywords + "\"";
                }
            }
            handleKeyWords = "(" + handleKeyWords + ")";
    }
        return handleKeyWords;
    }

    private static BoolQueryBuilder boolMultiSearchQuery(String keywords){
        BoolQueryBuilder builder;
        if("*".equalsIgnoreCase(keywords)|| StringUtils.isBlank(keywords)){
            return QueryBuilders.boolQuery().must(QueryBuilders.matchAllQuery());
        }
        builder = QueryBuilders.boolQuery();
        String[] keywordArray=keywords.split(" ");
        Integer length = keywordArray.length;
        if(length==1){
            return builder.must(QueryBuilders.multiMatchQuery(keywordArray[0], "*").type(MultiMatchQueryBuilder.Type.PHRASE_PREFIX));
        }
        for(int i =0;i<length;i++){
            if(i%2==0){ continue; }
            if(keywordArray[i].equalsIgnoreCase("AND")){
                builder.must(QueryBuilders.multiMatchQuery(keywordArray[i-1], "*").type(MultiMatchQueryBuilder.Type.PHRASE_PREFIX));
                if(i+1>length-1){ continue; }
                builder.must(QueryBuilders.multiMatchQuery(keywordArray[i+1], "*").type(MultiMatchQueryBuilder.Type.PHRASE_PREFIX));
            }else if(keywordArray[i].equalsIgnoreCase("OR")){
                if(i+1>length-1){ continue; }
                builder.should(QueryBuilders.multiMatchQuery(keywordArray[i+1], "*").type(MultiMatchQueryBuilder.Type.PHRASE_PREFIX));
            };
        }
        return builder;
    }


    //    2019-10-22T11:10:10.000Z
    public static void main(String[] args) {
//        System.out.println(EsDateTimeTransfrom("2019-10-22T11:10:10.000Z","yyyy-MM-dd'T'HH:mm:ss.SSS'Z'","yyyy-MM-dd HH:mm:ss"));
//        System.out.println(handKeyword("张三 and NOT 斗殴 "));
        String str = "(a and b) or c";
        System.out.println(boolMultiSearchQuery("(a and b) or c").toString(true));
    }
}
