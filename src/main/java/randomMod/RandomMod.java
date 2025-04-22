package randomMod;

import randomMod.relics.RandomFusionMonster;
import randomMod.helpers.Keyword;

import com.badlogic.gdx.graphics.Color;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.android.mods.AssetLoader;
import com.megacrit.cardcrawl.android.mods.BaseMod;
import com.megacrit.cardcrawl.android.mods.interfaces.*;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.potions.FairyPotion;
import com.megacrit.cardcrawl.potions.SmokeBomb;
import com.megacrit.cardcrawl.relics.MoltenEgg2;
import com.megacrit.cardcrawl.relics.PotionBelt;
import com.megacrit.cardcrawl.relics.PrayerWheel;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class RandomMod implements EditCardsSubscriber,
                                       PostInitializeSubscriber,
                                       EditStringsSubscriber,
                                       EditRelicsSubscriber,
                                       EditKeywordsSubscriber,PostBattleSubscriber {
    public static final String MOD_ID = "RandomMod";
    private static final Color YELLOW_COLOR = new Color(0.98F, 0.95F, 0.05F, 1.0F);

    public static void initialize() {
        new RandomMod();
    }

    public RandomMod() {
        BaseMod.subscribe(this);
    }

    public static String makeId(String name) {
        return MOD_ID + ":" + name;
    }

    public static String getResourcePath(String path) {
        return "RandomMod-TestImages/" + path;
    }

    @Override
    public void receiveEditCards() {

    }

    @Override
    public void receivePostInitialize() {
    }

    @Override
    public void receiveEditStrings() {
        String language;
        switch (Settings.language) {
            case ZHS:
                language = "zhs";
                break;
            default:
                language = "eng";
        }
        BaseMod.loadCustomStringsFile(MOD_ID, CardStrings.class, "RandomMod-localization/" + language + "/AndroidTest-CardStrings.json");
        BaseMod.loadCustomStringsFile(MOD_ID, RelicStrings.class, "RandomMod-localization/" + language + "/Random-RelicStrings.json");
    }

    @Override
    public void receiveEditRelics() {
        BaseMod.addRelic(new RandomFusionMonster());
    }

    @Override
    public void receiveEditKeywords() {
        String language;
        switch (Settings.language) {
            case ZHS:
                language = "zhs";
                break;
            default:
                language = "eng";
        }
        final Gson gson = new Gson();
        final String json = AssetLoader.getString(MOD_ID, "RandomMod-localization/" + language + "/Random-keyword.json");
        Keyword[] keywords = gson.fromJson(json, Keyword[].class);
        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }

    @Override
    public void receivePostBattle(AbstractRoom abstractRoom) {
        if(AbstractDungeon.player.hasRelic(RandomFusionMonster.ID))
        {return;}
        AbstractDungeon.getCurrRoom().spawnRelicAndObtain( (float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2), new RandomFusionMonster());
        AbstractDungeon.getCurrRoom().spawnRelicAndObtain( (float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2), new PrayerWheel());
        AbstractDungeon.getCurrRoom().spawnRelicAndObtain( (float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2),new PotionBelt());
        AbstractDungeon.getCurrRoom().spawnRelicAndObtain( (float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2),new MoltenEgg2());
        AbstractDungeon.player.obtainPotion(0,new FairyPotion());
        AbstractDungeon.player.obtainPotion(1,new SmokeBomb());

    }
}
