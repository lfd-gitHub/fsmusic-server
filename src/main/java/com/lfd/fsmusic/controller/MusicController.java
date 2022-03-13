package com.lfd.fsmusic.controller;

import com.lfd.fsmusic.base.ApiResponse;
import com.lfd.fsmusic.mapper.MusicMapper;
import com.lfd.fsmusic.service.MusicService;
import com.lfd.fsmusic.service.dto.in.MusicEditDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.security.RolesAllowed;

@Api(tags = "音乐接口")
@RequestMapping("/api/music")
@RestController
public class MusicController {

    private final MusicService musicService;
    private final MusicMapper mapper;

    public MusicController(MusicService musicService,MusicMapper mapper) {
        this.musicService = musicService;
        this.mapper = mapper;
    }

    @ApiOperation(value = "查找音乐")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(name = "sort", dataType = "string", paramType = "query"),
    })
    @GetMapping
    public ApiResponse list(@PageableDefault(sort = {
            "createTime" }, size = 2, direction = Sort.Direction.DESC) @ApiIgnore Pageable pageable){
        return ApiResponse.ok(musicService.list(pageable).map(mapper::toVo));
    }

    @ApiOperation(value = "音乐创建")
    @PostMapping
    @RolesAllowed("ROLE_ADMIN")
    public ApiResponse create(@Validated @RequestBody MusicEditDto dto){
        return ApiResponse.ok(mapper.toVo(musicService.create(dto)));
    }

    @ApiOperation(value = "音乐修改")
    @PutMapping("/{id}")
    @RolesAllowed("ROLE_ADMIN")
    public ApiResponse update(@PathVariable String id, @Validated @RequestBody MusicEditDto dto){
        return ApiResponse.ok(mapper.toVo(musicService.update(id,dto)));
    }

    @ApiOperation(value = "音乐发布")
    @PostMapping("/publish/{id}")
    public ApiResponse publish(@PathVariable String id){
        musicService.publish(id);
        return ApiResponse.ok();
    }

    @ApiOperation(value = "音乐关闭")
    @PostMapping("/close/{id}")
    public ApiResponse close(@PathVariable String id){
        musicService.close(id);
        return ApiResponse.ok();
    }


    @ApiOperation(value = "音乐删除")
    @DeleteMapping("/{id}")
    @RolesAllowed("ROLE_ADMIN")
    public ApiResponse delete(@PathVariable String id){
        musicService.delete(id);
        return ApiResponse.ok();
    }

}
