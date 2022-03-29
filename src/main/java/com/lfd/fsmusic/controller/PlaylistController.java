package com.lfd.fsmusic.controller;

import javax.annotation.security.RolesAllowed;

import com.lfd.fsmusic.base.ApiResponse;
import com.lfd.fsmusic.mapper.PlaylistMapper;
import com.lfd.fsmusic.service.PlaylistService;
import com.lfd.fsmusic.service.dto.in.PlaylistSaveReq;

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

@Api(tags = "音乐列表接口")
@RequestMapping("/api/playlist")
@RestController
@RequiredArgsConstructor(onConstructor_ = {@Lazy, @Autowired})
public class PlaylistController {

    private final PlaylistService playlistService;
    private final PlaylistMapper mapper;

    @ApiOperation(value = "查找音乐列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "Integer", dataTypeClass = Integer.class, paramType = "query"),
            @ApiImplicitParam(name = "size", dataType = "Integer", dataTypeClass = Integer.class, paramType = "query"),
            @ApiImplicitParam(name = "sort", dataType = "String", dataTypeClass = String.class, paramType = "query"),
    })
    @GetMapping
    public ApiResponse search(@PageableDefault(sort = {
            "createTime" }, size = 2, direction = Sort.Direction.DESC) @ApiIgnore Pageable pageable,String name) {
        return ApiResponse.ok(playlistService.search(name,pageable).map(mapper::toVo));
    }

    @ApiOperation(value = "音乐列表创建")
    @PostMapping
    public ApiResponse create(@Validated @RequestBody PlaylistSaveReq req) {
        return ApiResponse.ok(mapper.toVo(playlistService.create(mapper.fromReq(req))));
    }

    @ApiOperation(value = "音乐列表修改")
    @PutMapping("/{id}")
    public ApiResponse update(@PathVariable String id, @Validated @RequestBody PlaylistSaveReq req) {
        return ApiResponse.ok(mapper.toVo(playlistService.update(id, mapper.fromReq(req))));
    }

    @ApiOperation(value = "音乐列表发布")
    @PostMapping("/publish/{id}")
    public ApiResponse publish(@PathVariable String id) {
        playlistService.publish(id);
        return ApiResponse.ok();
    }

    @ApiOperation(value = "音乐列表关闭")
    @PostMapping("/close/{id}")
    public ApiResponse close(@PathVariable String id) {
        playlistService.close(id);
        return ApiResponse.ok();
    }

    @ApiOperation(value = "音乐列表删除")
    @DeleteMapping("/{id}")
    @RolesAllowed("ROLE_ADMIN")
    public ApiResponse delete(@PathVariable String id) {
        playlistService.delete(id);
        return ApiResponse.ok();
    }

}
