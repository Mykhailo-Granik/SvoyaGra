package com.svoyagra.data;

import com.svoyagra.domain.Player;

public interface PlayerRepository {

    void upsert(Player player);

}
