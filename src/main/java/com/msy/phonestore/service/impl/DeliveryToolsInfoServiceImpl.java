package com.msy.phonestore.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.msy.phonestore.dto.DeliveryToolsInfo;
import com.msy.phonestore.service.ifc.IDeliveryToolsInfoService;
import com.msy.phonestore.vo.ResCode;
import com.msy.phonestore.vo.ResponseModel;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/13/21:33
 * @Description:
 */
@Service
public class DeliveryToolsInfoServiceImpl implements IDeliveryToolsInfoService {
    @Override
    public ResponseModel findDeliveryMsg(Map<String, Object> map) throws Exception {
//
//        String host = "https://wuliu.market.alicloudapi.com";// 【1】请求地址 支持http 和 https 及 WEBSOCKET
//        String path = "/kdi";  // 【2】后缀
//        String appcode = "83dfbd3085a44173856733d0ce36815e"; // 【3】开通服务后 买家中心-查看AppCode
////        String no = "SF1145709153444:6364";// 【4】请求参数，详见文档描述
//        StringBuffer no=new StringBuffer();
//        if(map.get("deliveryType").equals("SFEXPRESS")){
//            no.append( map.get("deliveryId"));
//            System.out.println(map.get("deliveryId"));
//            no.append(":");
//            no.append(map.get("phoneNumber").toString().substring(7));
//        }else {
//            no.append(map.get("deliveryId").toString());
//        }
////        String type = "SFEXPRESS"; //  【4】请求参数，不知道可不填 95%能自动识别
//
//        String type=(String)map.get("deliveryType");
//        String urlSend = host + path + "?no=" + no.toString() +"&type="+type;  // 【5】拼接请求链接
//        try {
//            URL url = new URL(urlSend);
//            HttpURLConnection httpURLCon = (HttpURLConnection) url.openConnection();
//            httpURLCon .setRequestProperty("Authorization", "APPCODE " + appcode);// 格式Authorization:APPCODE (中间是英文空格)
//            int httpCode = httpURLCon.getResponseCode();
//            if (httpCode == 200) {
//                StringBuffer sb = new StringBuffer();
//                BufferedReader br = new BufferedReader(new InputStreamReader(httpURLCon.getInputStream()));
//                String line = null;
//                while ((line = br.readLine()) != null) {
//                    line = new String(line.getBytes(), "utf-8");
//                    sb.append(line);
//                }
//                br.close();
//                JSONObject jsonObject= JSON.parseObject(sb.toString());
//                Object result=jsonObject.get("result");
//
//                System.out.println(result);
//                DeliveryToolsInfo deliveryToolsInfo = JSONObject.parseObject(result.toString(), DeliveryToolsInfo.class);
//                return ResponseModel.success(ResCode.SUCCESS,deliveryToolsInfo);
//
//            } else {
//                Map<String, List<String>> map1 = httpURLCon.getHeaderFields();
//                String error = map1.get("X-Ca-Error-Message").get(0);
//                if (httpCode == 400 && error.equals("Invalid AppCode `not exists`")) {
//                    System.out.println("AppCode错误 ");
//                } else if (httpCode == 400 && error.equals("Invalid Url")) {
//                    System.out.println("请求的 Method、Path 或者环境错误");
//                } else if (httpCode == 400 && error.equals("Invalid Param Location")) {
//                    System.out.println("参数错误");
//                } else if (httpCode == 403 && error.equals("Unauthorized")) {
//                    System.out.println("服务未被授权（或URL和Path不正确）");
//                } else if (httpCode == 403 && error.equals("Quota Exhausted")) {
//                    System.out.println("套餐包次数用完 ");
//                } else {
//                    System.out.println("参数名错误 或 其他错误");
//                    System.out.println(error);
//                }
//            }
//
//        } catch (MalformedURLException e) {
//            System.out.println("URL格式错误");
//        } catch (UnknownHostException e) {
//            System.out.println("URL地址错误");
//        } catch (Exception e) {
//            // 打开注释查看详细报错异常信息
//            // e.printStackTrace();
//        }
//        return ResponseModel.fail(ResCode.FAIL);
        String str="{\"expName\":\"顺丰快递\",\"takeTime\":\"2天21小时47分\",\"expSite\":\"www.sf-express.com \",\"expPhone\":\"95338\",\"updateTime\":\"2022-01-13 08:44:47\",\"type\":\"SFEXPRESS\",\"list\":[{\"time\":\"2022-01-13 08:44:47\",\"status\":\"本人签收(关* )，感谢使用顺丰,期待再次为您服务（主单总件数：1件）\"},{\"time\":\"2022-01-13 07:47:47\",\"status\":\"快件交给齐俊奎,正在派送途中（联系电话：13811834076,顺丰已开启“安全呼叫”保护您的电话隐私,请放心接听！）（主单总件数：1件）\"},{\"time\":\"2022-01-13 07:10:17\",\"status\":\"快件到达 【北京朝阳将台营业点】\"},{\"time\":\"2022-01-13 05:17:08\",\"status\":\"快件已发车\"},{\"time\":\"2022-01-13 05:16:56\",\"status\":\"快件在【北京顺航中转场】完成分拣,准备发往 【北京朝阳将台营业点】\"},{\"time\":\"2022-01-13 02:33:09\",\"status\":\"快件到达 【北京顺航中转场】\"},{\"time\":\"2022-01-13 02:25:39\",\"status\":\"预计【01月13日】到达【北京市】，我们将在【01月13日夜间】为您更新快件状态，请您放心\"},{\"time\":\"2022-01-13 02:25:38\",\"status\":\"快件已发车\"},{\"time\":\"2022-01-13 02:15:27\",\"status\":\"快件在【北京南法信中转场】完成分拣,准备发往 【北京顺航中转场】\"},{\"time\":\"2022-01-12 21:16:02\",\"status\":\"快件到达 【北京南法信中转场】\"},{\"time\":\"2022-01-11 06:36:46\",\"status\":\"预计【01月13日】到达【北京市】，我们将在【01月11日上午】为您更新快件状态，请您放心\"},{\"time\":\"2022-01-11 06:36:45\",\"status\":\"快件已发车\"},{\"time\":\"2022-01-11 06:34:59\",\"status\":\"快件在【南宁六景中转场】完成分拣,准备发往 【北京南法信中转场】\"},{\"time\":\"2022-01-10 22:21:24\",\"status\":\"快件到达 【南宁六景中转场】\"},{\"time\":\"2022-01-10 21:11:58\",\"status\":\"快件已发车\"},{\"time\":\"2022-01-10 21:11:31\",\"status\":\"快件在【南宁江南中转场】完成分拣,准备发往 【南宁六景中转场】\"},{\"time\":\"2022-01-10 18:59:20\",\"status\":\"快件到达 【南宁江南中转场】\"},{\"time\":\"2022-01-10 16:59:32\",\"status\":\"快件已发车\"},{\"time\":\"2022-01-10 16:57:11\",\"status\":\"快件在【崇左市市直B区速运营业点】完成分拣,准备发往 【南宁江南中转场】\"},{\"time\":\"2022-01-10 10:57:03\",\"status\":\"顺丰速运 已收取快件\"}],\"issign\":\"1\",\"number\":\"SF1145709153444:6364\",\"deliverystatus\":\"3\",\"courier\":\"\",\"logo\":\"https://img3.fegine.com/express/sf.jpg\",\"courierPhone\":\"\"}\n";
        DeliveryToolsInfo deliveryToolsInfo = JSONObject.parseObject(str, DeliveryToolsInfo.class);
        return ResponseModel.success(ResCode.SUCCESS,deliveryToolsInfo);
    }
}
