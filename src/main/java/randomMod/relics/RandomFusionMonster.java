package randomMod.relics;

import randomMod.RandomMod;
import randomMod.utils.randommonster;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.unique.CanLoseAction;
import com.megacrit.cardcrawl.actions.unique.CannotLoseAction;
import com.megacrit.cardcrawl.actions.utility.HideHealthBarAction;
import com.megacrit.cardcrawl.android.mods.abstracts.CustomRelic;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.ending.CorruptHeart;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import java.util.Iterator;
import java.util.Random;

public class RandomFusionMonster extends CustomRelic {
    public static final String ID = RandomMod.makeId("RandomFusionMonster");
    public static final String IMG_PATH = RandomMod.getResourcePath("relics/runeOctahedron.png");

    public RandomFusionMonster() {
        super(RandomMod.MOD_ID, ID, IMG_PATH, RelicTier.RARE, LandingSound.SOLID);
        this.counter = 2;
    }


    @Override
    public void atBattleStart() {
        roll_monster();
    }
    public void roll_monster() {
        AbstractMonster m;
        Iterator var2 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();//获取所有怪物

        while (var2.hasNext()) {
            m = (AbstractMonster) var2.next();
            Random random = new Random();
            int randomNumber;

            AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(m, this));

            int s = AbstractDungeon.floorNum;
            AbstractMonster SP = null;

            if(s<=17) {
                randomNumber = AbstractDungeon.monsterRng.random(25)+1;

                System.out.println(randomNumber);
                SP = randommonster.randommonster(randomNumber);
            } else if (s<=34) {
                randomNumber =AbstractDungeon.monsterRng.random(22)+26;
                System.out.println(randomNumber);
                SP = randommonster.randommonster(randomNumber);
            }else if (s<=52) {
                randomNumber = AbstractDungeon.monsterRng.random(17)+49;
                System.out.println(randomNumber);
                SP = randommonster.randommonster(randomNumber);
            }else{

                randomNumber = AbstractDungeon.monsterRng.random(5)+63;
                System.out.println(randomNumber);
                SP = randommonster.randommonster(randomNumber);
                if(m.id== "CorruptHeart")
                {
                    SP = new CorruptHeart();
                }
            }

            SP.drawX = m.drawX;
            SP.drawY = m.drawY;//蛇花的位置
            SP.maxHealth = m.maxHealth;
            SP.addBlock(m.currentBlock);


            SP.currentHealth = m.currentHealth;


            AbstractPower po;
            for (AbstractPower power : m.powers) {
                // 对每个元素进行处理
                power.owner=SP;
                if (power.ID!="Split") {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(SP, SP, power));

                }
                else{

                }

            }
            if (m.maxHealth!=400) {
                AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(SP, false));
            }

            AbstractMonster finalSP = SP;
            AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
                public void update() {
                    finalSP.createIntent();

                    this.isDone = true;
                }
            });
            if (SP.id!="CorruptHeart") {
                SP.usePreBattleAction();
            }

            //AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(SP, false));
            if(AbstractDungeon.player.hasRelic("NeowsBlessing"))
            {
                if(AbstractDungeon.player.getRelic("NeowsBlessing").counter>0)
                {
                    SP.currentHealth=1;
                }

            }
            if(AbstractDungeon.player.hasRelic("PreservedInsect"))
            {
                if (AbstractDungeon.getCurrRoom().eliteTrigger) {
                    this.flash();


                    if (SP.currentHealth > (int)((float)SP.maxHealth * (1.0F - 0.25F))) {
                        SP.currentHealth = (int)((float)SP.maxHealth * (1.0F - 0.25F));
                        SP.healthBarUpdatedEvent();

                    }


                }

            } if(AbstractDungeon.player.hasRelic("Red Mask"))
            {
                this.addToBot(new ApplyPowerAction(SP, AbstractDungeon.player, new WeakPower(SP, 1, false), 1, true));

            }
            if(AbstractDungeon.player.hasRelic("Bag of Marbles"))
            {
                this.addToBot(new ApplyPowerAction(SP, AbstractDungeon.player, new VulnerablePower(SP, 1, false), 1, true));

            }
            if (m.maxHealth!=400) { AbstractDungeon.actionManager.addToBottom(new CannotLoseAction());

                AbstractDungeon.actionManager.addToBottom(new HideHealthBarAction(m));
                AbstractDungeon.actionManager.addToBottom(new SuicideAction(m, false));
                AbstractDungeon.actionManager.addToBottom(new CanLoseAction());}

        }}




    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }
}
