package com.lfd.fsmusic.controller;

import com.lfd.fsmusic.base.ApiResponse;
import com.lfd.fsmusic.mapper.ArtistMapper;
import com.lfd.fsmusic.service.ArtistService;
import com.lfd.fsmusic.service.dto.ArtistDto;
import com.lfd.fsmusic.service.dto.in.ArtistSaveReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "歌手管理")
@RequestMapping("/api/artist")
@RestController
@RequiredArgsConstructor(onConstructor_ = {@Lazy, @Autowired})
public class ArtistController {

    private final ArtistService artistService;
    private final ArtistMapper artistMapper;

    @ApiOperation(value = "查找歌手")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataTypeClass = Integer.class, paramType = "query"),
            @ApiImplicitParam(name = "size", dataTypeClass = Integer.class, paramType = "query"),
            @ApiImplicitParam(name = "sort", dataTypeClass = String.class, paramType = "query"),
    })
    @GetMapping
    public ApiResponse list(@PageableDefault(sort = {
            "createTime" }, size = 2, direction = Sort.Direction.DESC) @ApiIgnore Pageable pageable) {
        return ApiResponse.ok(artistService.list(pageable).map(artistMapper::toVo));
    }

    @PostMapping
    public ApiResponse create(@Validated @RequestBody ArtistSaveReq req){
        ArtistDto artistDto = artistService.create(req);
        return ApiResponse.ok(artistDto);
    }

    @PutMapping("/{id}")
    public ApiResponse update(@PathVariable String id,@Validated @RequestBody ArtistSaveReq req){
        ArtistDto artistDto = artistService.update(id,req);
        return ApiResponse.ok(artistDto);
    }

    @ApiOperation(value = "歌手发布")
    @PostMapping("/publish/{id}")
    public ApiResponse publish(@PathVariable String id) {
        return ApiResponse.ok(artistService.publish(id));
    }

    @ApiOperation(value = "歌手封禁")
    @PostMapping("/block/{id}")
    public ApiResponse block(@PathVariable String id) {
        return ApiResponse.ok(artistService.block(id));
    }

}
