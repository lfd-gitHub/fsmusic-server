package com.lfd.fsmusic.controller;

import javax.annotation.security.RolesAllowed;

import com.lfd.fsmusic.base.ApiResponse;
import com.lfd.fsmusic.mapper.MusicMapper;
import com.lfd.fsmusic.service.MusicService;
import com.lfd.fsmusic.service.dto.in.MusicSaveReq;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "音乐接口")
@RequestMapping("/api/music")
@RestController
@RequiredArgsConstructor(onConstructor_ = {@Lazy, @Autowired})
public class MusicController {

    private final MusicService musicService;
    private final MusicMapper mapper;

    @ApiOperation(value = "查找音乐")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataTypeClass = Integer.class, paramType = "query"),
            @ApiImplicitParam(name = "size", dataTypeClass = Integer.class, paramType = "query"),
            @ApiImplicitParam(name = "sort", dataTypeClass = String.class, paramType = "query"),
    })
    @GetMapping
    public ApiResponse search(@PageableDefault(sort = {
            "createTime" }, size = 2, direction = Sort.Direction.DESC) @ApiIgnore Pageable pageable,String name) {
        return ApiResponse.ok(musicService.search(name,pageable).map(mapper::toVo));
    }

    @ApiOperation(value = "音乐创建")
    @PostMapping
    @RolesAllowed("ROLE_ADMIN")
    public ApiResponse create(@Validated @RequestBody MusicSaveReq req) {
        return ApiResponse.ok(mapper.toVo(musicService.create(mapper.fromReq(req))));
    }

    @ApiOperation(value = "音乐修改")
    @PutMapping("/{id}")
    @RolesAllowed("ROLE_ADMIN")
    public ApiResponse update(@PathVariable String id, @Validated @RequestBody MusicSaveReq req) {
        return ApiResponse.ok(mapper.toVo(musicService.update(id, mapper.fromReq(req))));
    }

    @ApiOperation(value = "音乐发布")
    @PostMapping("/publish/{id}")
    public ApiResponse publish(@PathVariable String id) {
        musicService.publish(id);
        return ApiResponse.ok();
    }

    @ApiOperation(value = "音乐关闭")
    @PostMapping("/close/{id}")
    public ApiResponse close(@PathVariable String id) {
        musicService.close(id);
        return ApiResponse.ok();
    }

    @ApiOperation(value = "音乐删除")
    @DeleteMapping("/{id}")
    @RolesAllowed("ROLE_ADMIN")
    public ApiResponse delete(@PathVariable String id) {
        musicService.delete(id);
        return ApiResponse.ok();
    }

}
