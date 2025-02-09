package com.ldelaiglesia.tempedia.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.ldelaiglesia.tempedia.domain.models.Technique
import com.ldelaiglesia.tempedia.domain.models.TemtemDetail
import com.ldelaiglesia.tempedia.domain.models.TemtemEvolution

data class TemtemDetailDto(
    @SerializedName("number") val number: Int,
    @SerializedName("name") val name: String,
    @SerializedName("types") val types: List<String>,
    @SerializedName("stats") val stats: Stats,
    @SerializedName("portraitWikiUrl") val portraitList: String,
    @SerializedName("gameDescription") val gameDescription: String,
    @SerializedName("techniques") val techniques: List<Technique>,
    @SerializedName("evolution") val evolution: TemtemEvolution
)

data class Stats(
    @SerializedName("hp") val hp: Int,
    @SerializedName("sta") val sta: Int,
    @SerializedName("spd") val spd: Int,
    @SerializedName("atk") val atk: Int,
    @SerializedName("def") val def: Int,
    @SerializedName("spatk") val spatk: Int,
    @SerializedName("spdef") val spdef: Int,
    @SerializedName("total") val total: Int
)

fun TemtemDetailDto.toTemtemDetail(): TemtemDetail {
    return TemtemDetail(
        number = number,
        name = name,
        types = types,
        stats = mapOf(
            Pair("hp", stats.hp),
            Pair("sta", stats.sta),
            Pair("spd", stats.spd),
            Pair("atk", stats.atk),
            Pair("def", stats.def),
            Pair("spatk", stats.spatk),
            Pair("spdef", stats.spdef),
            Pair("total", stats.total)
        ),
        portraitList = portraitList,
        gameDescription = gameDescription,
        techniques = techniques,
        portrait = findTemtemPortrait(number),
        portraitGif = findTemtemPortraitGif(number),
        evolution = evolution
    )
}

fun findTemtemPortrait(number: Int): String {
    return when (number) {
        1 -> "https://temtem.wiki.gg/images/e/e2/Mimit_full_render.png"
        2 -> "https://temtem.wiki.gg/images/a/ac/Oree_full_render.png"
        3 -> "https://temtem.wiki.gg/images/e/e0/Zaobian_full_render.png"
        4 -> "https://temtem.wiki.gg/images/5/52/Chromeon_full_render.png"
        5 -> "https://temtem.wiki.gg/images/6/6f/Halzhi_full_render.png"
        6 -> "https://temtem.wiki.gg/images/3/3c/Molgu_full_render.png"
        7 -> "https://temtem.wiki.gg/images/7/7b/Platypet_full_render.png"
        8 -> "https://temtem.wiki.gg/images/d/d0/Platox_full_render.png"
        9 -> "https://temtem.wiki.gg/images/e/e9/Platimous_full_render.png"
        10 -> "https://temtem.wiki.gg/images/e/ed/Swali_full_render.png"
        11 -> "https://temtem.wiki.gg/images/1/1f/Loali_full_render.png"
        12 -> "https://temtem.wiki.gg/images/0/0e/Tateru_full_render.png"
        13 -> "https://temtem.wiki.gg/images/6/66/Gharunder_full_render.png"
        14 -> "https://temtem.wiki.gg/images/e/e1/Mosu_full_render.png"
        15 -> "https://temtem.wiki.gg/images/c/cb/Magmut_full_render.png"
        16 -> "https://temtem.wiki.gg/images/5/50/Paharo_full_render.png"
        17 -> "https://temtem.wiki.gg/images/3/3b/Paharac_full_render.png"
        18 -> "https://temtem.wiki.gg/images/8/8a/Granpah_full_render.png"
        19 -> "https://temtem.wiki.gg/images/f/f4/Ampling_full_render.png"
        20 -> "https://temtem.wiki.gg/images/9/93/Amphatyr_full_render.png"
        21 -> "https://temtem.wiki.gg/images/b/bb/Bunbun_full_render.png"
        22 -> "https://temtem.wiki.gg/images/e/e1/Mudrid_full_render.png"
        23 -> "https://temtem.wiki.gg/images/6/6a/Hidody_full_render.png"
        24 -> "https://temtem.wiki.gg/images/7/72/Taifu_full_render.png"
        25 -> "https://temtem.wiki.gg/images/0/05/Fomu_full_render.png"
        26 -> "https://temtem.wiki.gg/images/e/ed/Wiplump_full_render.png"
        27 -> "https://temtem.wiki.gg/images/3/39/Skail_full_render.png"
        28 -> "https://temtem.wiki.gg/images/d/d2/Skunch_full_render.png"
        29 -> "https://temtem.wiki.gg/images/2/20/Goty_full_render.png"
        30 -> "https://temtem.wiki.gg/images/9/99/Mouflank_full_render.png"
        31 -> "https://temtem.wiki.gg/images/9/91/Rhoulder_full_render.png"
        32 -> "https://temtem.wiki.gg/images/1/14/Houchic_full_render.png"
        33 -> "https://temtem.wiki.gg/images/1/1e/Tental_full_render.png"
        34 -> "https://temtem.wiki.gg/images/e/e9/Nagaise_full_render.png"
        35 -> "https://temtem.wiki.gg/images/5/5b/Orphyll_full_render.png"
        36 -> "https://temtem.wiki.gg/images/b/b0/Nidrasil_full_render.png"
        37 -> "https://temtem.wiki.gg/images/3/34/Banapi_full_render.png"
        38 -> "https://temtem.wiki.gg/images/9/95/Capyre_full_render.png"
        39 -> "https://temtem.wiki.gg/images/b/b0/Lapinite_full_render.png"
        40 -> "https://temtem.wiki.gg/images/b/b4/Azuroc_full_render.png"
        41 -> "https://temtem.wiki.gg/images/6/69/Zenoreth_full_render.png"
        42 -> "https://temtem.wiki.gg/images/d/d2/Reval_full_render.png"
        43 -> "https://temtem.wiki.gg/images/1/16/Aohi_full_render.png"
        44 -> "https://temtem.wiki.gg/images/a/a1/Bigu_full_render.png"
        45 -> "https://temtem.wiki.gg/images/4/46/Babawa_full_render.png"
        46 -> "https://temtem.wiki.gg/images/f/f0/0b1_full_render.png"
        47 -> "https://temtem.wiki.gg/images/2/27/0b10_full_render.png"
        48 -> "https://temtem.wiki.gg/images/3/35/Kaku_full_render.png"
        49 -> "https://temtem.wiki.gg/images/f/f0/Saku_full_render.png"
        50 -> "https://temtem.wiki.gg/images/e/ef/Valash_full_render.png"
        51 -> "https://temtem.wiki.gg/images/c/c0/Towly_full_render.png"
        52 -> "https://temtem.wiki.gg/images/6/6c/Owlhe_full_render.png"
        53 -> "https://temtem.wiki.gg/images/c/c2/Barnshe_full_render.png"
        54 -> "https://temtem.wiki.gg/images/e/e5/Gyalis_full_render.png"
        55 -> "https://temtem.wiki.gg/images/e/e7/Occlura_full_render.png"
        56 -> "https://temtem.wiki.gg/images/f/f7/Myx_full_render.png"
        57 -> "https://temtem.wiki.gg/images/e/e6/Raiber_full_render.png"
        58 -> "https://temtem.wiki.gg/images/7/7a/Raize_full_render.png"
        59 -> "https://temtem.wiki.gg/images/e/eb/Raican_full_render.png"
        60 -> "https://temtem.wiki.gg/images/9/91/Pewki_full_render.png"
        61 -> "https://temtem.wiki.gg/images/e/e3/Piraniant_full_render.png"
        62 -> "https://temtem.wiki.gg/images/7/72/Scarawatt_full_render.png"
        63 -> "https://temtem.wiki.gg/images/a/a6/Scaravolt_full_render.png"
        64 -> "https://temtem.wiki.gg/images/1/1a/Hoglip_full_render.png"
        65 -> "https://temtem.wiki.gg/images/2/29/Hedgine_full_render.png"
        66 -> "https://temtem.wiki.gg/images/f/f2/Osuchi_full_render.png"
        67 -> "https://temtem.wiki.gg/images/4/49/Osukan_full_render.png"
        68 -> "https://temtem.wiki.gg/images/8/82/Osukai_full_render.png"
        69 -> "https://temtem.wiki.gg/images/7/7a/Saipat_full_render.png"
        70 -> "https://temtem.wiki.gg/images/f/f2/Pycko_full_render.png"
        71 -> "https://temtem.wiki.gg/images/6/67/Drakash_full_render.png"
        72 -> "https://temtem.wiki.gg/images/b/b7/Crystle_full_render.png"
        73 -> "https://temtem.wiki.gg/images/0/00/Sherald_full_render.png"
        74 -> "https://temtem.wiki.gg/images/1/15/Tortenite_full_render.png"
        75 -> "https://temtem.wiki.gg/images/1/1c/Innki_full_render.png"
        76 -> "https://temtem.wiki.gg/images/4/4f/Shaolite_full_render.png"
        77 -> "https://temtem.wiki.gg/images/a/ab/Shaolant_full_render.png"
        78 -> "https://temtem.wiki.gg/images/5/5d/Cycrox_full_render.png"
        79 -> "https://temtem.wiki.gg/images/e/e2/Hocus_full_render.png"
        80 -> "https://temtem.wiki.gg/images/f/f5/Pocus_full_render.png"
        81 -> "https://temtem.wiki.gg/images/d/d9/Smolzy_full_render.png"
        82 -> "https://temtem.wiki.gg/images/e/eb/Sparzy_full_render.png"
        83 -> "https://temtem.wiki.gg/images/6/61/Golzy_full_render.png"
        84 -> "https://temtem.wiki.gg/images/d/d5/Mushi_full_render.png"
        85 -> "https://temtem.wiki.gg/images/5/58/Mushook_full_render.png"
        86 -> "https://temtem.wiki.gg/images/7/7c/Magmis_full_render.png"
        87 -> "https://temtem.wiki.gg/images/3/3e/Mastione_full_render.png"
        88 -> "https://temtem.wiki.gg/images/1/17/Umishi_full_render.png"
        89 -> "https://temtem.wiki.gg/images/8/80/Ukama_full_render.png"
        90 -> "https://temtem.wiki.gg/images/5/58/Galvanid_full_render.png"
        91 -> "https://temtem.wiki.gg/images/4/4a/Raignet_full_render.png"
        92 -> "https://temtem.wiki.gg/images/2/28/Smazee_full_render.png"
        93 -> "https://temtem.wiki.gg/images/c/c3/Baboong_full_render.png"
        94 -> "https://temtem.wiki.gg/images/b/b5/Seismunch_full_render.png"
        95 -> "https://temtem.wiki.gg/images/9/97/Zizare_full_render.png"
        96 -> "https://temtem.wiki.gg/images/4/4d/Gorong_full_render.png"
        97 -> "https://temtem.wiki.gg/images/4/43/Mitty_full_render.png"
        98 -> "https://temtem.wiki.gg/images/6/62/Sanbi_full_render.png"
        99 -> "https://temtem.wiki.gg/images/2/27/Momo_full_render.png"
        100 -> "https://temtem.wiki.gg/images/1/1e/Kuri_full_render.png"
        101 -> "https://temtem.wiki.gg/images/1/1c/Kauren_full_render.png"
        102 -> "https://temtem.wiki.gg/images/6/65/Spriole_full_render.png"
        103 -> "https://temtem.wiki.gg/images/7/72/Deendre_full_render.png"
        104 -> "https://temtem.wiki.gg/images/e/e9/Cerneaf_full_render.png"
        105 -> "https://temtem.wiki.gg/images/e/ec/Toxolotl_full_render.png"
        106 -> "https://temtem.wiki.gg/images/e/e8/Noxolotl_full_render.png"
        107 -> "https://temtem.wiki.gg/images/2/2f/Blooze_full_render.png"
        108 -> "https://temtem.wiki.gg/images/2/29/Goolder_full_render.png"
        109 -> "https://temtem.wiki.gg/images/6/6f/Zephyruff_full_render.png"
        110 -> "https://temtem.wiki.gg/images/3/36/Volarend_full_render.png"
        111 -> "https://temtem.wiki.gg/images/d/d0/Grumvel_full_render.png"
        112 -> "https://temtem.wiki.gg/images/7/7d/Grumper_full_render.png"
        113 -> "https://temtem.wiki.gg/images/b/b8/Ganki_full_render.png"
        114 -> "https://temtem.wiki.gg/images/6/6c/Gazuma_full_render.png"
        115 -> "https://temtem.wiki.gg/images/3/37/Oceara_full_render.png"
        116 -> "https://temtem.wiki.gg/images/5/5e/Yowlar_full_render.png"
        117 -> "https://temtem.wiki.gg/images/f/f1/Droply_full_render.png"
        118 -> "https://temtem.wiki.gg/images/6/61/Garyo_full_render.png"
        119 -> "https://temtem.wiki.gg/images/6/62/Broccoblin_full_render.png"
        120 -> "https://temtem.wiki.gg/images/4/4a/Broccorc_full_render.png"
        121 -> "https://temtem.wiki.gg/images/d/d6/Broccolem_full_render.png"
        122 -> "https://temtem.wiki.gg/images/5/52/Shuine_full_render.png"
        123 -> "https://temtem.wiki.gg/images/d/d0/Nessla_full_render.png"
        124 -> "https://temtem.wiki.gg/images/c/c0/Valiar_full_render.png"
        125 -> "https://temtem.wiki.gg/images/5/55/Pupoise_full_render.png"
        126 -> "https://temtem.wiki.gg/images/2/2c/Loatle_full_render.png"
        127 -> "https://temtem.wiki.gg/images/c/c9/Kalazu_full_render.png"
        128 -> "https://temtem.wiki.gg/images/6/62/Kalabyss_full_render.png"
        129 -> "https://temtem.wiki.gg/images/a/a6/Adoroboros_full_render.png"
        130 -> "https://temtem.wiki.gg/images/2/28/Tuwai_full_render.png"
        131 -> "https://temtem.wiki.gg/images/5/5f/Tukai_full_render.png"
        132 -> "https://temtem.wiki.gg/images/7/78/Tulcan_full_render.png"
        133 -> "https://temtem.wiki.gg/images/d/d5/Tuvine_full_render.png"
        134 -> "https://temtem.wiki.gg/images/e/e6/Turoc_full_render.png"
        135 -> "https://temtem.wiki.gg/images/1/12/Tuwire_full_render.png"
        136 -> "https://temtem.wiki.gg/images/6/60/Tutsu_full_render.png"
        137 -> "https://temtem.wiki.gg/images/9/9d/Kinu_full_render.png"
        138 -> "https://temtem.wiki.gg/images/c/c1/Vulvir_full_render.png"
        139 -> "https://temtem.wiki.gg/images/2/2b/Vulor_full_render.png"
        140 -> "https://temtem.wiki.gg/images/2/2b/Vulcrane_full_render.png"
        141 -> "https://temtem.wiki.gg/images/8/80/Pigepic_full_render.png"
        142 -> "https://temtem.wiki.gg/images/2/2b/Akranox_full_render.png"
        143 -> "https://temtem.wiki.gg/images/c/c7/Koish_full_render.png"
        144 -> "https://temtem.wiki.gg/images/5/58/Vulffy_full_render.png"
        145 -> "https://temtem.wiki.gg/images/e/e6/Chubee_full_render.png"
        146 -> "https://temtem.wiki.gg/images/6/62/Waspeen_full_render.png"
        147 -> "https://temtem.wiki.gg/images/6/69/Mawtle_full_render.png"
        148 -> "https://temtem.wiki.gg/images/d/d8/Mawmense_full_render.png"
        149 -> "https://temtem.wiki.gg/images/7/72/Hazrat_full_render.png"
        150 -> "https://temtem.wiki.gg/images/8/82/Minttle_full_render.png"
        151 -> "https://temtem.wiki.gg/images/4/48/Minox_full_render.png"
        152 -> "https://temtem.wiki.gg/images/1/13/Minothor_full_render.png"
        153 -> "https://temtem.wiki.gg/images/3/35/Maoala_full_render.png"
        154 -> "https://temtem.wiki.gg/images/8/84/Venx_full_render.png"
        155 -> "https://temtem.wiki.gg/images/b/b0/Venmet_full_render.png"
        156 -> "https://temtem.wiki.gg/images/a/a3/Vental_full_render.png"
        157 -> "https://temtem.wiki.gg/images/b/b2/Chimurian_full_render.png"
        158 -> "https://temtem.wiki.gg/images/b/b9/Arachnyte_full_render.png"
        159 -> "https://temtem.wiki.gg/images/b/bb/Thaiko_full_render.png"
        160 -> "https://temtem.wiki.gg/images/f/ff/Monkko_full_render.png"
        161 -> "https://temtem.wiki.gg/images/1/19/Anahir_full_render.png"
        162 -> "https://temtem.wiki.gg/images/e/e4/Anatan_full_render.png"
        163 -> "https://temtem.wiki.gg/images/7/76/Tyranak_full_render.png"
        164 -> "https://temtem.wiki.gg/images/0/0d/Volgon_full_render.png"
        165 -> "https://temtem.wiki.gg/images/6/66/Galios_full_render.png"
        else -> ""
    }
}

fun findTemtemPortraitGif(number: Int): String {
    return when (number) {
        1 -> "https://temtem.wiki.gg/images/9/94/Mimit_idle_animation.gif"
        2 -> "https://temtem.wiki.gg/images/9/9a/Oree_idle_animation.gif"
        3 -> "https://temtem.wiki.gg/images/2/20/Zaobian_idle_animation.gif"
        4 -> "https://temtem.wiki.gg/images/f/f5/Chromeon_idle_animation.gif"
        5 -> "https://temtem.wiki.gg/images/4/4a/Halzhi_idle_animation.gif"
        6 -> "https://temtem.wiki.gg/images/5/53/Molgu_idle_animation.gif"
        7 -> "https://temtem.wiki.gg/images/9/95/Platypet_idle_animation.gif"
        8 -> "https://temtem.wiki.gg/images/b/bb/Platox_idle_animation.gif"
        9 -> "https://temtem.wiki.gg/images/a/a8/Platimous_idle_animation.gif"
        10 -> "https://temtem.wiki.gg/images/f/f2/Swali_idle_animation.gif"
        11 -> "https://temtem.wiki.gg/images/6/6f/Loali_idle_animation.gif"
        12 -> "https://temtem.wiki.gg/images/b/b0/Tateru_idle_animation.gif"
        13 -> "https://temtem.wiki.gg/images/5/5f/Gharunder_idle_animation.gif"
        14 -> "https://temtem.wiki.gg/images/4/4f/Mosu_idle_animation.gif"
        15 -> "https://temtem.wiki.gg/images/2/2f/Magmut_idle_animation.gif"
        16 -> "https://temtem.wiki.gg/images/4/40/Paharo_idle_animation.gif"
        17 -> "https://temtem.wiki.gg/images/9/99/Paharac_idle_animation.gif"
        18 -> "https://temtem.wiki.gg/images/d/df/Granpah_idle_animation.gif"
        19 -> "https://temtem.wiki.gg/images/2/2f/Ampling_idle_animation.gif"
        20 -> "https://temtem.wiki.gg/images/7/73/Amphatyr_idle_animation.gif"
        21 -> "https://temtem.wiki.gg/images/2/22/Bunbun_idle_animation.gif"
        22 -> "https://temtem.wiki.gg/images/2/20/Mudrid_idle_animation.gif"
        23 -> "https://temtem.wiki.gg/images/9/92/Hidody_idle_animation.gif"
        24 -> "https://temtem.wiki.gg/images/a/a2/Taifu_idle_animation.gif"
        25 -> "https://temtem.wiki.gg/images/b/be/Fomu_idle_animation.gif"
        26 -> "https://temtem.wiki.gg/images/c/cb/Wiplump_idle_animation.gif"
        27 -> "https://temtem.wiki.gg/images/0/0a/Skail_idle_animation.gif"
        28 -> "https://temtem.wiki.gg/images/4/42/Skunch_idle_animation.gif"
        29 -> "https://temtem.wiki.gg/images/f/ff/Goty_idle_animation.gif"
        30 -> "https://temtem.wiki.gg/images/8/8d/Mouflank_idle_animation.gif"
        31 -> "https://temtem.wiki.gg/images/b/b6/Rhoulder_idle_animation.gif"
        32 -> "https://temtem.wiki.gg/images/4/41/Houchic_idle_animation.gif"
        33 -> "https://temtem.wiki.gg/images/9/96/Tental_idle_animation.gif"
        34 -> "https://temtem.wiki.gg/images/1/18/Nagaise_idle_animation.gif"
        35 -> "https://temtem.wiki.gg/images/2/2c/Orphyll_idle_animation.gif"
        36 -> "https://temtem.wiki.gg/images/2/2d/Nidrasil_idle_animation.gif"
        37 -> "https://temtem.wiki.gg/images/b/b0/Banapi_idle_animation.gif"
        38 -> "https://temtem.wiki.gg/images/9/9a/Capyre_idle_animation.gif"
        39 -> "https://temtem.wiki.gg/images/3/39/Lapinite_idle_animation.gif"
        40 -> "https://temtem.wiki.gg/images/8/82/Azuroc_idle_animation.gif"
        41 -> "https://temtem.wiki.gg/images/f/f9/Zenoreth_idle_animation.gif"
        42 -> "https://temtem.wiki.gg/images/2/21/Reval_idle_animation.gif"
        43 -> "https://temtem.wiki.gg/images/a/a8/Aohi_idle_animation.gif"
        44 -> "https://temtem.wiki.gg/images/b/b8/Bigu_idle_animation.gif"
        45 -> "https://temtem.wiki.gg/images/0/0f/Babawa_idle_animation.gif"
        46 -> "https://temtem.wiki.gg/images/e/eb/0b1_idle_animation.gif"
        47 -> "https://temtem.wiki.gg/images/c/c9/0b10_idle_animation.gif"
        48 -> "https://temtem.wiki.gg/images/8/89/Kaku_idle_animation.gif"
        49 -> "https://temtem.wiki.gg/images/5/58/Saku_idle_animation.gif"
        50 -> "https://temtem.wiki.gg/images/2/26/Valash_idle_animation.gif"
        51 -> "https://temtem.wiki.gg/images/0/04/Towly_idle_animation.gif"
        52 -> "https://temtem.wiki.gg/images/a/a0/Owlhe_idle_animation.gif"
        53 -> "https://temtem.wiki.gg/images/5/57/Barnshe_idle_animation.gif"
        54 -> "https://temtem.wiki.gg/images/b/b0/Gyalis_idle_animation.gif"
        55 -> "https://temtem.wiki.gg/images/3/36/Occlura_idle_animation.gif"
        56 -> "https://temtem.wiki.gg/images/0/06/Myx_idle_animation.gif"
        57 -> "https://temtem.wiki.gg/images/7/7b/Raiber_idle_animation.gif"
        58 -> "https://temtem.wiki.gg/images/f/f1/Raize_idle_animation.gif"
        59 -> "https://temtem.wiki.gg/images/b/b2/Raican_idle_animation.gif"
        60 -> "https://temtem.wiki.gg/images/d/df/Pewki_idle_animation.gif"
        61 -> "https://temtem.wiki.gg/images/2/23/Piraniant_idle_animation.gif"
        62 -> "https://temtem.wiki.gg/images/4/42/Scarawatt_idle_animation.gif"
        63 -> "https://temtem.wiki.gg/images/f/f5/Scaravolt_idle_animation.gif"
        64 -> "https://temtem.wiki.gg/images/2/2c/Hoglip_idle_animation.gif"
        65 -> "https://temtem.wiki.gg/images/1/1d/Hedgine_idle_animation.gif"
        66 -> "https://temtem.wiki.gg/images/7/70/Osuchi_idle_animation.gif"
        67 -> "https://temtem.wiki.gg/images/0/07/Osukan_idle_animation.gif"
        68 -> "https://temtem.wiki.gg/images/1/15/Osukai_idle_animation.gif"
        69 -> "https://temtem.wiki.gg/images/8/85/Saipat_idle_animation.gif"
        70 -> "https://temtem.wiki.gg/images/2/2b/Pycko_idle_animation.gif"
        71 -> "https://temtem.wiki.gg/images/e/e2/Drakash_idle_animation.gif"
        72 -> "https://temtem.wiki.gg/images/8/8e/Crystle_idle_animation.gif"
        73 -> "https://temtem.wiki.gg/images/6/67/Sherald_idle_animation.gif"
        74 -> "https://temtem.wiki.gg/images/b/b2/Tortenite_idle_animation.gif"
        75 -> "https://temtem.wiki.gg/images/5/55/Innki_idle_animation.gif"
        76 -> "https://temtem.wiki.gg/images/4/4a/Shaolite_idle_animation.gif"
        77 -> "https://temtem.wiki.gg/images/2/2f/Shaolant_idle_animation.gif"
        78 -> "https://temtem.wiki.gg/images/2/2c/Cycrox_idle_animation.gif"
        79 -> "https://temtem.wiki.gg/images/8/88/Hocus_idle_animation.gif"
        80 -> "https://temtem.wiki.gg/images/5/56/Pocus_idle_animation.gif"
        81 -> "https://temtem.wiki.gg/images/0/07/Smolzy_idle_animation.gif"
        82 -> "https://temtem.wiki.gg/images/0/0b/Sparzy_idle_animation.gif"
        83 -> "https://temtem.wiki.gg/images/3/3a/Golzy_idle_animation.gif"
        84 -> "https://temtem.wiki.gg/images/8/8d/Mushi_idle_animation.gif"
        85 -> "https://temtem.wiki.gg/images/0/0a/Mushook_idle_animation.gif"
        86 -> "https://temtem.wiki.gg/images/f/f8/Magmis_idle_animation.gif"
        87 -> "https://temtem.wiki.gg/images/3/35/Mastione_idle_animation.gif"
        88 -> "https://temtem.wiki.gg/images/f/f9/Umishi_idle_animation.gif"
        89 -> "https://temtem.wiki.gg/images/8/8f/Ukama_idle_animation.gif"
        90 -> "https://temtem.wiki.gg/images/0/0c/Galvanid_idle_animation.gif"
        91 -> "https://temtem.wiki.gg/images/a/ad/Raignet_idle_animation.gif"
        92 -> "https://temtem.wiki.gg/images/8/88/Smazee_idle_animation.gif"
        93 -> "https://temtem.wiki.gg/images/d/dc/Baboong_idle_animation.gif"
        94 -> "https://temtem.wiki.gg/images/f/fa/Seismunch_idle_animation.gif"
        95 -> "https://temtem.wiki.gg/images/c/c0/Zizare_idle_animation.gif"
        96 -> "https://temtem.wiki.gg/images/e/e3/Gorong_idle_animation.gif"
        97 -> "https://temtem.wiki.gg/images/e/e7/Mitty_idle_animation.gif"
        98 -> "https://temtem.wiki.gg/images/3/32/Sanbi_idle_animation.gif"
        99 -> "https://temtem.wiki.gg/images/a/ad/Momo_idle_animation.gif"
        100 -> "https://temtem.wiki.gg/images/e/ed/Kuri_idle_animation.gif"
        101 -> "https://temtem.wiki.gg/images/8/81/Kauren_idle_animation.gif"
        102 -> "https://temtem.wiki.gg/images/7/70/Spriole_idle_animation.gif"
        103 -> "https://temtem.wiki.gg/images/6/60/Deendre_idle_animation.gif"
        104 -> "https://temtem.wiki.gg/images/0/08/Cerneaf_idle_animation.gif"
        105 -> "https://temtem.wiki.gg/images/d/d8/Toxolotl_idle_animation.gif"
        106 -> "https://temtem.wiki.gg/images/1/13/Noxolotl_idle_animation.gif"
        107 -> "https://temtem.wiki.gg/images/9/9d/Blooze_idle_animation.gif"
        108 -> "https://temtem.wiki.gg/images/c/c7/Goolder_idle_animation.gif"
        109 -> "https://temtem.wiki.gg/images/e/e3/Zephyruff_idle_animation.gif"
        110 -> "https://temtem.wiki.gg/images/f/fb/Volarend_idle_animation.gif"
        111 -> "https://temtem.wiki.gg/images/5/51/Grumvel_idle_animation.gif"
        112 -> "https://temtem.wiki.gg/images/b/b3/Grumper_idle_animation.gif"
        113 -> "https://temtem.wiki.gg/images/2/2d/Ganki_idle_animation.gif"
        114 -> "https://temtem.wiki.gg/images/d/db/Gazuma_idle_animation.gif"
        115 -> "https://temtem.wiki.gg/images/7/74/Oceara_idle_animation.gif"
        116 -> "https://temtem.wiki.gg/images/b/b3/Yowlar_idle_animation.gif"
        117 -> "https://temtem.wiki.gg/images/b/be/Droply_idle_animation.gif"
        118 -> "https://temtem.wiki.gg/images/9/9f/Garyo_idle_animation.gif"
        119 -> "https://temtem.wiki.gg/images/f/fb/Broccoblin_idle_animation.gif"
        120 -> "https://temtem.wiki.gg/images/1/1d/Broccorc_idle_animation.gif"
        121 -> "https://temtem.wiki.gg/images/2/27/Broccolem_idle_animation.gif"
        122 -> "https://temtem.wiki.gg/images/7/7c/Shuine_idle_animation.gif"
        123 -> "https://temtem.wiki.gg/images/7/77/Nessla_idle_animation.gif"
        124 -> "https://temtem.wiki.gg/images/6/6b/Valiar_idle_animation.gif"
        125 -> "https://temtem.wiki.gg/images/7/7b/Pupoise_idle_animation.gif"
        126 -> "https://temtem.wiki.gg/images/b/b5/Loatle_idle_animation.gif"
        127 -> "https://temtem.wiki.gg/images/2/2d/Kalazu_idle_animation.gif"
        128 -> "https://temtem.wiki.gg/images/0/07/Kalabyss_idle_animation.gif"
        129 -> "https://temtem.wiki.gg/images/3/35/Adoroboros_idle_animation.gif"
        130 -> "https://temtem.wiki.gg/images/4/4c/Tuwai_idle_animation.gif"
        131 -> "https://temtem.wiki.gg/images/7/73/Tukai_idle_animation.gif"
        132 -> "https://temtem.wiki.gg/images/d/d3/Tulcan_idle_animation.gif"
        133 -> "https://temtem.wiki.gg/images/d/df/Tuvine_idle_animation.gif"
        134 -> "https://temtem.wiki.gg/images/4/4f/Turoc_idle_animation.gif"
        135 -> "https://temtem.wiki.gg/images/d/d0/Tuwire_idle_animation.gif"
        136 -> "https://temtem.wiki.gg/images/e/ec/Tutsu_idle_animation.gif"
        137 -> "https://temtem.wiki.gg/images/7/7f/Kinu_idle_animation.gif"
        138 -> "https://temtem.wiki.gg/images/0/0a/Vulvir_idle_animation.gif"
        139 -> "https://temtem.wiki.gg/images/f/f1/Vulor_idle_animation.gif"
        140 -> "https://temtem.wiki.gg/images/0/0e/Vulcrane_idle_animation.gif"
        141 -> "https://temtem.wiki.gg/images/d/dc/Pigepic_idle_animation.gif"
        142 -> "https://temtem.wiki.gg/images/8/82/Akranox_idle_animation.gif"
        143 -> "https://temtem.wiki.gg/images/3/32/Koish_idle_animation.gif"
        144 -> "https://temtem.wiki.gg/images/3/35/Vulffy_idle_animation.gif"
        145 -> "https://temtem.wiki.gg/images/e/e0/Chubee_idle_animation.gif"
        146 -> "https://temtem.wiki.gg/images/b/b1/Waspeen_idle_animation.gif"
        147 -> "https://temtem.wiki.gg/images/f/f2/Mawtle_idle_animation.gif"
        148 -> "https://temtem.wiki.gg/images/d/d6/Mawmense_idle_animation.gif"
        149 -> "https://temtem.wiki.gg/images/0/0f/Hazrat_idle_animation.gif"
        150 -> "https://temtem.wiki.gg/images/a/a9/Minttle_idle_animation.gif"
        151 -> "https://temtem.wiki.gg/images/9/91/Minox_idle_animation.gif"
        152 -> "https://temtem.wiki.gg/images/b/b6/Minothor_idle_animation.gif"
        153 -> "https://temtem.wiki.gg/images/c/c9/Maoala_idle_animation.gif"
        154 -> "https://temtem.wiki.gg/images/6/6c/Venx_idle_animation.gif"
        155 -> "https://temtem.wiki.gg/images/5/51/Venmet_idle_animation.gif"
        156 -> "https://temtem.wiki.gg/images/a/a8/Vental_idle_animation.gif"
        157 -> "https://temtem.wiki.gg/images/f/f8/Chimurian_idle_animation.gif"
        158 -> "https://temtem.wiki.gg/images/f/f2/Arachnyte_idle_animation.gif"
        159 -> "https://temtem.wiki.gg/images/3/38/Thaiko_idle_animation.gif"
        160 -> "https://temtem.wiki.gg/images/b/bf/Monkko_idle_animation.gif"
        161 -> "https://temtem.wiki.gg/images/d/d7/Anahir_idle_animation.gif"
        162 -> "https://temtem.wiki.gg/images/5/56/Anatan_idle_animation.gif"
        163 -> "https://temtem.wiki.gg/images/7/70/Tyranak_idle_animation.gif"
        164 -> "https://temtem.wiki.gg/images/8/88/Volgon_idle_animation.gif"
        165 -> "https://temtem.wiki.gg/images/4/4f/Galios_idle_animation.gif"
        else -> ""
    }
}