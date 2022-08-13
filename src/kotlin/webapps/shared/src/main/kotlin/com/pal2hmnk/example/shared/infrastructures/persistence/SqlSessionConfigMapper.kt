package com.pal2hmnk.example.shared.infrastructures.persistence

import org.apache.ibatis.session.Configuration

typealias SqlSessionConfigMapper = Configuration.() -> Unit
