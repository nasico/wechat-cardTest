package com.example.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.nasico.security.ShaUtils;
import com.nasico.wechat.offcialaccounts.ApiUtils;
import com.nasico.wechat.offcialaccounts.Signature;

@RestController
public class HelloController {

//	@GetMapping
//	public String hello(Hello e) {
//		System.out.println("get");
//		return e.toString();
//	}
//	
//	@PostMapping
//	public String hello2(Hello e) {
//		System.out.println("post");
//		return e.toString();
//	}

	/**
	 * 第一次配置用
	 * @param notifyData
	 * @return
	 */
//	@RequestMapping("wechat/signature")
//	public String signature(String signature, String timestamp, String nonce, String echostr) {
//		String token = "wZC6SqZe1qCffrF7SQCARrc06QC0R56d";
//		System.out.println(signature);
//		System.out.println(timestamp);
//		System.out.println(nonce);
//		System.out.println(echostr);
//		return Signature.check(signature, timestamp, nonce, echostr, token);
//	}
	

	/**
	 *  配置成功后将接口改为接收回调推送的接口 
	 *  https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1451025274
	 * @param notifyData
	 * @return
	 */
	@RequestMapping("wechat/signature")
	public void signature(@RequestBody String notifyData) {
		System.out.println(notifyData);
		//将回调信息中需要的数据保存到本地
//		<xml><ToUserName><![CDATA[gh_e7aa45ab139d]]></ToUserName>
//		<FromUserName><![CDATA[ohW_H1VGxe6QVt0w4jvyJ8Lv4XtQ]]></FromUserName>
//		<CreateTime>1558968863</CreateTime>
//		<MsgType><![CDATA[event]]></MsgType>
//		<Event><![CDATA[user_get_card]]></Event>
//		<CardId><![CDATA[phW_H1XI2M1nm6dUoINLxUUzzZy8]]></CardId>
//		<IsGiveByFriend>0</IsGiveByFriend>
//		<UserCardCode><![CDATA[927983162925]]></UserCardCode>
//		<FriendUserName><![CDATA[]]></FriendUserName>
//		<OuterId>0</OuterId>
//		<OldUserCardCode><![CDATA[]]></OldUserCardCode>
//		<IsRestoreMemberCard>0</IsRestoreMemberCard>
//		<IsRecommendByFriend>0</IsRecommendByFriend>
//		<SourceScene><![CDATA[SOURCE_SCENE_UNKNOW]]></SourceScene>
//		<UnionId><![CDATA[oAAAAAHbl9CwWJLFKEIkJhOKd1Pg]]></UnionId>
//		</xml>
	}


	@RequestMapping("/getCardSign")
	public String sign() {
		String cardId = "phW_H1XI2M1nm6dUoINLxUUzzZy8";
		String nonce_str = "asd123";
		int timestamp = (int) (System.currentTimeMillis() / 1000);
		String ticket = "9KwiourQPRN3vx3Nn1c_icRcEVdeuHfaenbAiKY5h4pupDfG0qHDYQgx7a3kvxaXRsLOHz471j8O4QnRqWhCMA";
		
		List<String> params = new ArrayList<String>(3);
		params.add(cardId);
		params.add(nonce_str);
		params.add(ticket);
		params.add(Integer.toString(timestamp));
		Collections.sort(params);
		
		StringBuilder sb= new StringBuilder();
		for (String s : params) {
			sb.append(s);
		}
		
		System.out.println(sb.toString());
		String signature = ShaUtils.encode(sb.toString());
		
		JSONObject obj = new JSONObject();
		obj.put("cardId", cardId);
		obj.put("timestamp", timestamp);
		obj.put("ticket", ticket);
		obj.put("signature", signature);
		obj.put("nonce_str", nonce_str);
		System.out.println(signature);
		return obj.toJSONString();
	}
	
	public static void main(String[] args) {
		// TODO 1、获取token
		String token = ApiUtils.getToken();
		// TODO 2、上传logo
		
		// TODO 3、创建卡券
		// TODO 4、创建二维码投放
		// TODO 5、显示二维码
		// TODO 6、设置测试白名单 这一步是测试开发时使用。正式环境不需要
		// TODO 6、核销卡劵
	}
}
