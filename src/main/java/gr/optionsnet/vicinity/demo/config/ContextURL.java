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

package gr.optionsnet.vicinity.demo.config;

public interface ContextURL {

    String MVC_HOME = "index";
    String MVC_SAMMY = "sammy";
    String MVC_SYSTEM = "system";


    String REST_ROOT = "rest/";
    String REST_SAMMY = "sammy";
    String REST_ITEMS = "items";
    String REST_LOG = "log";
    String REST_ITEM_LOGIN = REST_ITEMS + "/login";
    String REST_ITEM_QUERY = REST_ITEMS + "/query";


}
