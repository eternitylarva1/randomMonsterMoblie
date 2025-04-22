package unlockAll;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.android.mods.BaseMod;
import com.megacrit.cardcrawl.android.mods.interfaces.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.Prefs;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.helpers.SaveHelper;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import java.util.ArrayList;

public class UnlockAll implements EditCardsSubscriber,
        PostInitializeSubscriber,
        EditStringsSubscriber,
        EditRelicsSubscriber,
        EditKeywordsSubscriber,PostBattleSubscriber {
    public static final String MOD_ID = "UnlockAll";
    private static final Color YELLOW_COLOR = new Color(0.98F, 0.95F, 0.05F, 1.0F);

    public static void initialize() {
        new UnlockAll();
    }

    public UnlockAll() {
        BaseMod.subscribe(this);
    }

    public static String makeId(String name) {
        return MOD_ID + ":" + name;
    }

    public static String getResourcePath(String path) {
        return "UnlockAll-TestImages/" + path;
    }

    @Override
    public void receiveEditCards() {

    }

    public void receivePostInitialize() {

        unlockAscensionLevel();
        unlockFinalAct();
        unlockDaily();
        unlockBetaArtAndEnding();
        unlockRelics();
        unlockCards();
    }

    public static void unlockBetaArtAndEnding() {
        String key = "THE_ENDING";
        if (!UnlockTracker.achievementPref.getBoolean(key, false)) {
            UnlockTracker.achievementPref.putBoolean(key, true);
            UnlockTracker.achievementPref.flush();
        }
    }

    public static void unlockFinalAct() {
        CardCrawlGame.playerPref.putBoolean(String.valueOf(AbstractPlayer.PlayerClass.IRONCLAD.name()) + "_WIN", true);
        CardCrawlGame.playerPref.putBoolean(String.valueOf(AbstractPlayer.PlayerClass.THE_SILENT.name()) + "_WIN", true);
        CardCrawlGame.playerPref.putBoolean(String.valueOf(AbstractPlayer.PlayerClass.DEFECT.name()) + "_WIN", true);
        CardCrawlGame.playerPref.flush();
    }

    public static void unlockDaily() {
        ArrayList<Prefs> prefs = new ArrayList<>();
        prefs.add(SaveHelper.getPrefs("STSDataVagabond"));
        prefs.add(SaveHelper.getPrefs("STSDataTheSilent"));
        prefs.add(SaveHelper.getPrefs("STSDataDefect"));
        prefs.add(SaveHelper.getPrefs("STSDataWatcher"));
        boolean hasBossKill = false;
        for (Prefs p : prefs) {
            if (p.getInteger("BOSS_KILL", 0) > 0) {
                hasBossKill = true;
                break;
            }
        }
        if (!hasBossKill) {
            prefs.get(0).putInteger("BOSS_KILL", 1);
            prefs.get(0).flush();
        }
    }

    public static void unlockCards() {
        CardLibrary.initialize();
        for (String id : unlockAll.CardLibrary.CARD_IDS) {
            UnlockTracker.unlockPref.putInteger(id, 2);
            UnlockTracker.lockedCards.remove(id);
            AbstractCard c = CardLibrary.getCard(id);
            if (c != null && !c.isSeen) {
                UnlockTracker.markCardAsSeen(c.cardID);
                c.isSeen = true;
                c.unlock();
                UnlockTracker.seenPref.putInteger(id, 1);
            }
        }
        UnlockTracker.seenPref.flush();
        UnlockTracker.unlockPref.flush();
    }

    public static void unlockRelics() {
        RelicLibrary.initialize();
        for (String id : unlockAll.RelicLibrary.RELIC_IDS) {
            UnlockTracker.unlockPref.putInteger(id, 2);
            UnlockTracker.lockedRelics.remove(id);
            AbstractRelic r = RelicLibrary.getRelic(id);
            if (r != null && !r.isSeen) {
                UnlockTracker.markRelicAsSeen(r.relicId);
                r.isSeen = true;
                UnlockTracker.relicSeenPref.putInteger(id, 1);
            }
        }
        UnlockTracker.unlockPref.flush();
        UnlockTracker.relicSeenPref.flush();
    }

    public static void unlockAscensionLevel() {
        UnlockTracker.hardUnlockOverride("The Silent");
        UnlockTracker.hardUnlockOverride("Defect");
        UnlockTracker.hardUnlockOverride("Watcher");
        ArrayList<Prefs> allPrefs = CardCrawlGame.characterManager.getAllPrefs();
        for (int i = 0; i < Math.min(allPrefs.size(), 4); i++) {
            Prefs pref = allPrefs.get(i);
            if (pref.getInteger("WIN_COUNT", 0) == 0)
                pref.putInteger("WIN_COUNT", 1);
            pref.putInteger("ASCENSION_LEVEL", 20);
            pref.putInteger("LAST_ASCENSION_LEVEL", 20);
            pref.flush();
        }
        String[] bosses = new String[] { "CROW", "DONUT", "WIZARD" };
        for (String boss : bosses) {
            UnlockTracker.markBossAsSeen(boss);
        }
    }

    @Override
    public void receiveEditStrings() {
    }

    @Override
    public void receiveEditRelics() {

    }

    @Override
    public void receiveEditKeywords() {

    }

    @Override
    public void receivePostBattle(AbstractRoom abstractRoom) {

    }
}
