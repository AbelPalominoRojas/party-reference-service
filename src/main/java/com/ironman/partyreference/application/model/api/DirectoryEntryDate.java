package com.ironman.partyreference.application.model.api;

import java.time.LocalDateTime;

public record DirectoryEntryDate(
    DirectoryEntryDateType directoryEntryDateType, LocalDateTime directoryEntryDate) {}
