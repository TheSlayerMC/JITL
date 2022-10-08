package net.jitl.common.entity;

import net.jitl.client.gui.BossBarRenderer;
import net.jitl.core.helper.JMusic;

public interface IJourneyBoss {

    BossBarRenderer getBossBar();

    JMusic getBossMusic();
}