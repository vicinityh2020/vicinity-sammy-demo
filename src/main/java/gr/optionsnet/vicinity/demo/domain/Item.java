/*
 * Copyright © 2019 OptionsNet/ All rights reserved.
 *
 * This file is part of SaMMY@VICINITY integration demo.
 *
 * SaMMY@VICINITY integration demo is free software: you can redistribute it
 * and/or modify it under the terms of GNU GPL.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT ANY WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT, IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * See README file for the full disclaimer information and LICENSE file for full license information in the project root.
 */

package gr.optionsnet.vicinity.demo.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
@Table(name = "item")
public class Item implements Serializable {

    /**
     * OID from configuration is the public ObjectID
     * InfraId from configuration is the internal ObjectID ans equals OID field from adapter/objects response
     */
    @Id
    @Column(name = "oid", columnDefinition = "varchar (36)")
    private String oid;

    @Column(name = "infraId", columnDefinition = "varchar (36)", unique = true)
    private String infraId;

    @Column(name = "password", columnDefinition = "varchar (255)")
    private String password;

    @Column(name = "name", columnDefinition = "varchar (255)")
    private String name;

    @Column(name = "type", columnDefinition = "varchar (255)")
    private String type;

    @Column(name = "pid", columnDefinition = "varchar (255)")
    private String pid;

    @Column(name = "monitors", columnDefinition = "varchar (255)")
    private String monitors;

    @Column(name = "units", columnDefinition = "varchar (255) default ''")
    private String units;

}
