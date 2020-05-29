package com.zte.clonedata.util;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.ParserConfig;

import java.util.LinkedList;
import java.util.List;

/**
 * ProjectName: clonedata-com.zte.clonedata.util
 *
 * @Author: Liang Xiaomin
 * @Date: Creating in 18:02 2020/5/28
 * @Description:
 */
public class JSONUtils {

    private JSONUtils(){}

    public static  <T> List<T> parseArray(String data, Class<T> clazz) {
        DefaultJSONParser parser = new DefaultJSONParser(data, ParserConfig.getGlobalInstance());
        JSONLexer lexer = parser.lexer;
        int token = lexer.token();
        List list;
        if (token == 8) {
            lexer.nextToken();
            list = null;
        } else if (token == 20 && lexer.isBlankInput()) {
            list = null;
        } else {
            list = new LinkedList();
            parser.parseArray(clazz, list);
            parser.handleResovleTask(list);
        }

        parser.close();
        return list;
    }
}
