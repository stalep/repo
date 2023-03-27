package io.hyperfoil.tools.horreum.mapper;

import io.hyperfoil.tools.horreum.entity.json.*;

import java.util.Collections;
import java.util.stream.Collectors;

public class ViewMapper {
    public static ViewDTO from(View v) {
        ViewDTO dto = new ViewDTO();
        dto.id = v.id;
        dto.name = v.name;
        dto.testId = v.test.id;
        if(v.components != null)
            dto.components = v.components.stream().map(ViewMapper::fromViewComponent).collect(Collectors.toList());

        return dto;
    }

    public static ViewComponentDTO fromViewComponent(ViewComponent vc) {
        ViewComponentDTO dto = new ViewComponentDTO();
        dto.id = vc.id;
        dto.headerName = vc.headerName;
        dto.headerOrder = vc.headerOrder;
        dto.labels = vc.labels;
        dto.render = vc.render;

        return dto;
    }

    public static View to(ViewDTO dto) {
        View v = new View();
        v.id = dto.id;
        v.name = dto.name;
        if(dto.testId != null && dto.testId > 0)
            v.test = View.getEntityManager().getReference(Test.class, dto.testId);
        if(dto.components != null)
            v.components = dto.components.stream().map(c -> ViewMapper.toViewComponent(c, v)).collect(Collectors.toList());
        else
            v.components = Collections.emptyList();

        return v;
    }

    private static ViewComponent toViewComponent(ViewComponentDTO dto, View view) {
        ViewComponent vc = new ViewComponent();
        vc.id = dto.id;
        vc.headerName = dto.headerName;
        vc.headerOrder = dto.headerOrder;
        vc.labels = dto.labels;
        vc.render = dto.render;
        vc.view = view;

        return vc;
    }
}
