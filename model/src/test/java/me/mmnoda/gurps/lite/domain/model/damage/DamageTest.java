package me.mmnoda.gurps.lite.domain.model.damage;

/*
 * #%L
 * model
 * %%
 * Copyright (C) 2016 Márcio Noda
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import org.junit.Test;

import static me.mmnoda.gurps.lite.domain.model.damage.DamageType.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

/**
 *
 */
public class DamageTest {

    private Damage damage;

    private Damage finalDamage;

    @Test
    public void should_calculate_final_damage_for_crush_type() {
        buildDamage(50, CRUSH);
        finalValue();
        assertFinalDamageIsEqualsTo(damage);
    }

    @Test
    public void should_calculate_final_damage_for_cut_type() {
        buildDamage(9, CUT);
        finalValue();
        assertFinalDamageIsEqualsTo(Damage.of(13, CUT));
    }

    @Test
    public void should_calculate_final_damage_for_small_piercing_type() {
        buildDamage(10, SMALL_PIERCING);
        finalValue();
        assertFinalDamageIsEqualsTo(Damage.of(5, SMALL_PIERCING));
    }

    @Test
    public void should_calculate_final_damage_for_piercing_type() {
        buildDamage(7, PIERCING);
        finalValue();
        assertFinalDamageIsEqualsTo(Damage.of(7, PIERCING));
    }

    @Test
    public void should_calculate_final_damage_for_large_piercing_type() {
        buildDamage(15, LARGE_PIERCING);
        finalValue();
        assertFinalDamageIsEqualsTo(Damage.of(22, LARGE_PIERCING));
    }

    @Test
    public void should_calculate_final_damage_for_huge_piercing_type() {
        buildDamage(20, HUGE_PIERCING);
        finalValue();
        assertFinalDamageIsEqualsTo(Damage.of(40, HUGE_PIERCING));
    }

    @Test
    public void should_calculate_final_damage_for_impaling_type() {
        buildDamage(2, IMPALING);
        finalValue();
        assertFinalDamageIsEqualsTo(Damage.of(4, IMPALING));
    }

    @Test
    public void should_calculate_final_damage_for_burning_type() {
        buildDamage(5, BURNING);
        finalValue();
        assertFinalDamageIsEqualsTo(damage);
    }

    @Test
    public void should_calculate_final_damage_for_toxic_type() {
        buildDamage(1, TOXIC);
        finalValue();
        assertFinalDamageIsEqualsTo(damage);
    }

    @Test
    public void should_calculate_final_damage_for_corrosive_type() {
        buildDamage(3, CORROSIVE);
        finalValue();
        assertFinalDamageIsEqualsTo(damage);
    }

    @Test
    public void should_calculate_final_damage_for_fatigue_type() {
        buildDamage(4, FATIGUE);
        finalValue();
        assertFinalDamageIsEqualsTo(damage);
    }

    @Test(expected = IllegalStateException.class)
    public void should_not_allow_calculate_final_damage_of_final_value() {
        buildDamage(8, CUT);
        finalValue();
        finalDamage.finalValue();
        fail("Final Value should not be calculated");
    }

    @Test
    public void should_format() {
        buildDamage(100, FATIGUE);
        final String formatted = String.format("%s", damage);
        assertThat(formatted)
                .isNotNull()
                .isNotEmpty()
                .isEqualTo("100 [fat]");
    }

    private void assertFinalDamageIsEqualsTo(Damage of) {
        assertThat(finalDamage)
                .isNotNull()
                .isEqualTo(of);
    }

    private void buildDamage(int value, DamageType type) {
        damage = Damage.of(value, type);
    }

    private void finalValue() {
        finalDamage = damage.finalValue();
    }
}