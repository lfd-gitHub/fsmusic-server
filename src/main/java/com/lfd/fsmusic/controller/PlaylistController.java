package com.lfd.fsmusic.controller;

import com.lfd.fsmusic.base.ApiResponse;
import com.lfd.fsmusic.mapper.PlaylistMapper;
import com.lfd.fsmusic.service.PlaylistService;
import com.lfd.fsmusic.service.dto.in.PlaylistSaveReq;
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

@Api(tags = "音乐列表接口")
@RequestMapping("/api/playlist")
@RestController
public class PlaylistController {

    private final PlaylistService playlistService;
    private final PlaylistMapper mapper;

    public PlaylistController(PlaylistService playlistService, PlaylistMapper mapper) {
        this.playlistService = playlistService;
        this.mapper = mapper;
    }

    @ApiOperation(value = "查找音乐列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(name = "sort", dataType = "string", paramType = "query"),
    })
    @GetMapping
    public ApiResponse list(@PageableDefault(sort = {
            "createTime" }, size = 2, direction = Sort.Direction.DESC) @ApiIgnore Pageable pageable) {
        return ApiResponse.ok(playlistService.list(pageable).map(mapper::toVo));
    }

    @ApiOperation(value = "音乐列表创建")
    @PostMapping
    public ApiResponse create(@Validated @RequestBody PlaylistSaveReq dto) {
        return ApiResponse.ok(mapper.toVo(playlistService.create(dto)));
    }

    @ApiOperation(value = "音乐列表修改")
    @PutMapping("/{id}")
    public ApiResponse update(@PathVariable String id, @Validated @RequestBody PlaylistSaveReq dto) {
        return ApiResponse.ok(mapper.toVo(playlistService.update(id, dto)));
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
