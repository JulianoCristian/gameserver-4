package com.game.params.gang;

import java.util.List;
import java.util.ArrayList;
import com.game.params.*;

//公会列表(工具自动生成，请勿手动修改！）
public class GangList implements IProtocol {
	public List<GangInfo> gangs;//公会列表
	public int page;//总页数
	public int curPage;//当前页


	public void decode(BufferBuilder bb) {
		
        if (bb.getNullFlag())
            this.gangs = null;
        else {
            int length = bb.getInt();
            this.gangs = new ArrayList<GangInfo>();
            for (int i = 0; i < length; i++)
            {
                //如果元素不够先创建一个，Java泛型创建对象，性能？
                boolean isNull = bb.getNullFlag();

                //如果不是null就解析
                if(isNull)
                {
                    this.gangs.add(null);
                }
                else
                {
                    GangInfo instance = new GangInfo();
                    instance.decode(bb);
                    this.gangs.add(instance);
                }

            }
        }
		this.page = bb.getInt();
		this.curPage = bb.getInt();
	}

	public void encode(BufferBuilder bb) {
		bb.putProtocolVoList(this.gangs);
		bb.putInt(this.page);
		bb.putInt(this.curPage);
	}
}
