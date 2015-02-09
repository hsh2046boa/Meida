package com.meida.emall.protocol;

/*
 *	 ______    ______    ______
 *	/\  __ \  /\  ___\  /\  ___\
 *	\ \  __<  \ \  __\_ \ \  __\_
 *	 \ \_____\ \ \_____\ \ \_____\
 *	  \/_____/  \/_____/  \/_____/
 *
 *
 *	Copyright (c) 2013-2014, {Bee} open source community
 *	http://www.bee-framework.com
 *
 *
 *	Permission is hereby granted, free of charge, to any person obtaining a
 *	copy of this software and associated documentation files (the "Software"),
 *	to deal in the Software without restriction, including without limitation
 *	the rights to use, copy, modify, merge, publish, distribute, sublicense,
 *	and/or sell copies of the Software, and to permit persons to whom the
 *	Software is furnished to do so, subject to the following conditions:
 *
 *	The above copyright notice and this permission notice shall be included in
 *	all copies or substantial portions of the Software.
 *
 *	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 *	FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 *	IN THE SOFTWARE.
 */
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.external.activeandroid.Model;
import com.external.activeandroid.annotation.Column;
import com.external.activeandroid.annotation.Table;

@Table(name = "ORDER_INFO")
public class ORDER_INFO extends Model
{
    @Column(name = "pay_code")
    public String pay_code;

    @Column(name = "order_amount")
    public String order_amount;

    @Column(name = "order_id")
    public int order_id;

    @Column(name = "order_sn")
    public String order_sn;

    @Column(name = "subject")
    public String subject;

    @Column(name = "desc")
    public String desc;

    public static ORDER_INFO fromJson(JSONObject jsonObject)  throws JSONException
    {
        if(null == jsonObject){
            return null;
        }

        ORDER_INFO   localItem = new ORDER_INFO();

        JSONArray subItemArray;

        localItem.pay_code = jsonObject.optString("pay_code");

        localItem.order_amount = jsonObject.optString("order_amount");

        localItem.order_id  = jsonObject.optInt("order_id");

        localItem.subject   = jsonObject.optString("subject");

        localItem.desc      = jsonObject.optString("desc");

        localItem.order_sn  = jsonObject.optString("order_sn");
        return localItem;
    }

    public JSONObject  toJson() throws JSONException
    {
        JSONObject localItemObject = new JSONObject();
        JSONArray itemJSONArray = new JSONArray();
        localItemObject.put("pay_code", pay_code);
        localItemObject.put("order_amount",order_amount);
        localItemObject.put("order_id",order_id);
        localItemObject.put("subject",subject);
        localItemObject.put("desc",desc);
        localItemObject.put("order_sn",order_sn);
        return localItemObject;
    }


}
