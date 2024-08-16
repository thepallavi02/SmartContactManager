package com.project.scm.Helpers;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class Messages {
    private String content;
    @Builder.Default
    private MessageType type=MessageType.Blue;
}
