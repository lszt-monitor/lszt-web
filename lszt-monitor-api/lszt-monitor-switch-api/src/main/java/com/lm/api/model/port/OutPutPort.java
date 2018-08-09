package com.lm.api.model.port;

import java.util.Date;

public class OutPutPort extends PetPort  {
	
	/**
	 * @param timePortOut
	 * 继承父类
	 */
	//26
	public OutPutPort(Integer id_id_output, Integer port1, Integer port2, Integer port3, Integer port4,
			Integer port5, Integer port6, Integer port7, Integer port8, Integer port9, Integer port10, Integer port11,
			Integer port12, Integer port13, Integer port14, Integer port15, Integer port16, Integer port17,
			Integer port18, Integer port19, Integer port20, Integer port21, Integer port22, Integer port23,
			Integer port24, Integer port25, Integer port26, Date timePortOut) {
		super(id_id_output, port1, port2, port3, port4, port5, port6, port7, port8, port9, port10, port11, port12,
				port13, port14, port15, port16, port17, port18, port19, port20, port21, port22, port23, port24, port25,
				port26, timePortOut);
	}
	//24
	public OutPutPort( Integer id_id_output, Integer port1, Integer port2, Integer port3, Integer port4,
			Integer port5, Integer port6, Integer port7, Integer port8, Integer port9, Integer port10, Integer port11,
			Integer port12, Integer port13, Integer port14, Integer port15, Integer port16, Integer port17,
			Integer port18, Integer port19, Integer port20, Integer port21, Integer port22, Integer port23,
			Integer port24, Date timePortOut) {
		super( id_id_output, port1, port2, port3, port4, port5, port6, port7, port8, port9, port10, port11, port12,
				port13, port14, port15, port16, port17, port18, port19, port20, port21, port22, port23, port24,
				 timePortOut);
	}
}
