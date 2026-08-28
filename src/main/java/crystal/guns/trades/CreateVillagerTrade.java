package crystal.guns.trades;

import crystal.guns.enchantment.EnchantmentKeys;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;
import net.minecraft.village.VillagerProfession;

import java.util.Optional;


public class CreateVillagerTrade {
    private CreateVillagerTrade() {
        /* This utility class should not be instantiated */
    }

    @SuppressWarnings("java:S3776")
    public static void createTrades(){
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.LIBRARIAN, 1, factories -> {



            //region DECAY

            factories.add((entity, random) -> {

                if (random.nextFloat() >= 0.02) {
                    return null;
                }
                final ItemStack decay = EnchantedBookItem.forEnchantment(
                        new EnchantmentLevelEntry(
                                entity.getWorld().getRegistryManager().get(RegistryKeys.ENCHANTMENT)
                                        .getEntry(EnchantmentKeys.DECAY)
                                        .orElseThrow(), 1
                        ));

                return new TradeOffer(
                        new TradedItem(Items.EMERALD, random.nextBetween(5, 19)),
                        Optional.of(new TradedItem(Items.BOOK, 1)),

                        decay, 12, random.nextBetween(3, 6), 0.04F
                );
            });
            //endregion
            //region Frostbite
            factories.add((entity, random) -> {

                if (random.nextFloat() >= 0.02) {
                    return null;
                }

                final ItemStack frost = EnchantedBookItem.forEnchantment(
                        new EnchantmentLevelEntry(
                                entity.getWorld().getRegistryManager().get(RegistryKeys.ENCHANTMENT)
                                        .getEntry(EnchantmentKeys.FROST)
                                        .orElseThrow(), 1
                        ));

                return new TradeOffer(
                        new TradedItem(Items.EMERALD, random.nextBetween(5, 19)),
                        Optional.of(new TradedItem(Items.BOOK, 1)),

                        frost, 12, random.nextBetween(3, 6), 0.04F
                );
            });
            //endregion
            //region Catalyst
            factories.add((entity, random) -> {

                final int level = random.nextBetween(1, 4);

                if (random.nextFloat() >= 0.02) {
                    return null;
                }
                final ItemStack catalyst = EnchantedBookItem.forEnchantment(
                        new EnchantmentLevelEntry(
                                entity.getWorld().getRegistryManager().get(RegistryKeys.ENCHANTMENT)
                                        .getEntry(EnchantmentKeys.CATALYST)
                                        .orElseThrow(), level
                        ));
                return new TradeOffer(
                        new TradedItem(Items.EMERALD, random.nextBetween(5 * level, Math.min(19 * level, 50))),
                        Optional.of(new TradedItem(Items.BOOK, 1)),

                        catalyst, 12, random.nextBetween(3, 6), 0.04F
                );
            });
            //endregion
            //region Shrapnel
            factories.add((entity, random) -> {
                final int level = random.nextBetween(1, 3);

                if (random.nextFloat() >= 0.02) {
                    return null;
                }

                final ItemStack shrapnel = EnchantedBookItem.forEnchantment(
                        new EnchantmentLevelEntry(
                                entity.getWorld().getRegistryManager().get(RegistryKeys.ENCHANTMENT)
                                        .getEntry(EnchantmentKeys.SHRAPNEL)
                                        .orElseThrow(), level
                        ));

                return new TradeOffer(
                        new TradedItem(Items.EMERALD, random.nextBetween(5 * level, Math.min(19 * level, 50))),
                        Optional.of(new TradedItem(Items.BOOK, 1)),

                        shrapnel, 12, random.nextBetween(3, 6), 0.04F
                );
            });
            //endregion
            //region Quick Shot
            factories.add((entity, random) -> {
            final int level = random.nextBetween(1, 5);
                if (random.nextFloat() >= 0.02) {
                    return null;
                }

                final ItemStack quickshot = EnchantedBookItem.forEnchantment(
                        new EnchantmentLevelEntry(
                                entity.getWorld().getRegistryManager().get(RegistryKeys.ENCHANTMENT)
                                        .getEntry(EnchantmentKeys.QUICK_SHOT)
                                        .orElseThrow(), level
                        ));

                return new TradeOffer(
                        new TradedItem(Items.EMERALD, random.nextBetween(level, Math.min(19 * level, 50))),
                        Optional.of(new TradedItem(Items.BOOK, 1)),

                        quickshot, 12, random.nextBetween(3, 6), 0.04F
                );
            });
            //endregion
            //region Reserve
            factories.add((entity, random) -> {

                if (random.nextFloat() >= 0.02) {
                    return null;
                }

                final ItemStack reserve = EnchantedBookItem.forEnchantment(
                        new EnchantmentLevelEntry(
                                entity.getWorld().getRegistryManager().get(RegistryKeys.ENCHANTMENT)
                                        .getEntry(EnchantmentKeys.MAGAZINE_EXPANSION)
                                        .orElseThrow(), 1
                        ));

                return new TradeOffer(
                        new TradedItem(Items.EMERALD,random.nextBetween(5, 19)),
                        Optional.of(new TradedItem(Items.BOOK, 1)),

                        reserve, 12, random.nextBetween(3, 6), 0.04F
                );
            });
            //endregion
        });
    }

}
