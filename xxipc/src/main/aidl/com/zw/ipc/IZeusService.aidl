// IZeusService.aidl
package com.zw.ipc;

import com.zw.ipc.Request;
import com.zw.ipc.Response;

interface IZeusService {
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    Response postRequest(in Request request);
}
