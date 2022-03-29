package com.lfd.fsmusic.mapper.base;

import com.lfd.fsmusic.controller.vo.BaseVo;
import com.lfd.fsmusic.service.dto.BaseDto;

public interface IVoMapper<Vo extends BaseVo,Dto extends BaseDto>{

    Vo toVo(Dto dto);

}
